package pl.nepapp.ksp.processor

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo

class NavigationProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return NavigationProcessor(environment)
    }
}

class NavigationProcessor(
    environment: SymbolProcessorEnvironment
) : SymbolProcessor {
    private val codeGenerator = environment.codeGenerator

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val myNavKey = resolver
            .getClassDeclarationByName(
                resolver.getKSNameFromString("pl.nepapp.rasoth.core.navigation.BaseScreen")
            ) ?: return emptyList()

        val subclasses = resolver
            .getSymbolsWithAnnotation("kotlinx.serialization.Serializable")
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.isSubclassOf(myNavKey) }
            .toList()

        if (subclasses.isEmpty()) return emptyList()

        generateFile(subclasses)

        return emptyList()
    }

    private fun KSClassDeclaration.isSubclassOf(base: KSClassDeclaration): Boolean {
        return this.superTypes.any {
            val resolved = it.resolve().declaration
            resolved == base || (resolved as? KSClassDeclaration)?.isSubclassOf(base) == true
        }
    }

    private fun generateFile(classes: List<KSClassDeclaration>) {

        val navKeyClass = ClassName("androidx.navigation3.runtime", "NavKey")
        val polymorphicFn = MemberName("kotlinx.serialization.modules", "polymorphic")

        val funSpec = FunSpec.builder("registerNavKeys")
            .receiver(ClassName("kotlinx.serialization.modules", "SerializersModuleBuilder"))
            .addCode(buildCodeBlock {
                add("%M(%T::class) {\n", polymorphicFn, navKeyClass)
                indent()
                classes.forEach {
                    val className = it.toClassName()
                    add(
                        "subclass(%T::class, %T.serializer())\n",
                        className,
                        className
                    )
                }
                unindent()
                add("}\n")
            })
            .build()

        val fileSpec = FileSpec.builder(
            "pl.nepapp.rasoth.shared.generated",
            "NavigationModule"
        )
            .addFunction(funSpec)
            .build()

        fileSpec.writeTo(
            codeGenerator,
            Dependencies(false, *classes.mapNotNull { it.containingFile }.toTypedArray())
        )
    }
}

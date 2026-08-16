package pl.nepapp.rasoth

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainActivityTest {

    @Test
    fun appContextUsesCorrectPackageName() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        assertEquals("pl.nepapp.rasoth", appContext.packageName)
    }

    @Test
    fun mainActivityLaunchesAndResumes() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertEquals("pl.nepapp.rasoth", activity.packageName)
            }
        }
    }
}

package pl.nepapp.rasoth

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoginScreenTemplateTest {

    @Test
    fun loginScreen_template_appContextIsCorrect() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("pl.nepapp.rasoth", appContext.packageName)
    }

    @Test
    fun loginScreen_template_activityLaunches() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertEquals("pl.nepapp.rasoth", activity.packageName)
            }
        }
    }
}

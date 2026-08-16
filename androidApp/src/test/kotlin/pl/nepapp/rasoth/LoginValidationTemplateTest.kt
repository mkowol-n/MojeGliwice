package pl.nepapp.rasoth

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pl.nepapp.rasoth.core.ui.input.validators.EmailValidator
import pl.nepapp.rasoth.core.ui.input.validators.PasswordValidator

@DisplayName("Login validation template (JUnit 5)")
class LoginValidationTemplateTest {

    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()

    @ParameterizedTest(name = "accepts valid email: {0}")
    @ValueSource(
        strings = [
            "john.doe@example.com",
            "user+tag@sub.domain.org",
        ]
    )
    fun `email validator accepts valid emails`(email: String) {
        assertNull(emailValidator.validate(email))
    }

    @ParameterizedTest(name = "rejects invalid email: {0}")
    @ValueSource(
        strings = [
            "",
            "invalid",
            "@example.com",
            "user@",
        ]
    )
    fun `email validator rejects invalid emails`(email: String) {
        assertNotNull(emailValidator.validate(email))
    }

    @Test
    fun `password validator accepts strong password`() {
        assertNull(passwordValidator.validate("Strong1!"))
    }

    @Test
    fun `password validator rejects weak password`() {
        assertNotNull(passwordValidator.validate("weak"))
    }
}

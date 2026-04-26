package pl.nepapp.rasoth.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import pl.nepapp.rasoth.core.navigation.BaseScreen
import pl.nepapp.rasoth.core.navigation.LocalNavigator
import pl.nepapp.rasoth.core.ui.input.BaseInputField
import pl.nepapp.rasoth.features.registration.RegistrationScreen
import org.jetbrains.compose.resources.stringResource
import rasoth.composeapp.generated.resources.Res
import rasoth.composeapp.generated.resources.email_label
import rasoth.composeapp.generated.resources.email_placeholder
import rasoth.composeapp.generated.resources.login_button
import rasoth.composeapp.generated.resources.no_account_prompt
import rasoth.composeapp.generated.resources.password_label
import rasoth.composeapp.generated.resources.password_placeholder
import rasoth.composeapp.generated.resources.register_button

@Serializable
data object LoginScreen : BaseScreen {
    @Composable
    override fun Content() {
        LoginContent()
    }
}

@Suppress("ParamsComparedByRef")
@Composable
private fun LoginContent(viewModel: LoginViewModel = koinViewModel()) {
    val state by viewModel.collectAsState()
    val navigator = LocalNavigator.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = stringResource(Res.string.login_button),
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(32.dp))

        BaseInputField(
            state = state.emailField,
            label = stringResource(Res.string.email_label),
            placeholder = stringResource(Res.string.email_placeholder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        BaseInputField(
            state = state.passwordField,
            label = stringResource(Res.string.password_label),
            placeholder = stringResource(Res.string.password_placeholder),
            isPassword = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            } else {
                Text(text = stringResource(Res.string.login_button))
            }
        }

        state.authError?.let { authError ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = authError.asString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(Res.string.no_account_prompt),
                style = MaterialTheme.typography.bodyMedium,
            )
            TextButton(onClick = { navigator?.push(RegistrationScreen) }) {
                Text(text = stringResource(Res.string.register_button))
            }
        }
    }
}

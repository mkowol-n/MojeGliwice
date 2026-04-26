package pl.nepapp.rasoth.core.auth


sealed class SocialAuthFailedException: RuntimeException() {
    class FirebaseUserMissingException: SocialAuthFailedException()
    class FirebaseIdTokenMissingException: SocialAuthFailedException()
    class FacebookLoginRequiresNativeSDKIntegrationException: SocialAuthFailedException()
}
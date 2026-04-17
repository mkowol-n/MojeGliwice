package pl.nepapp.rasoth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package pl.nepapp.rasoth

import android.app.Application
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            workManagerFactory()
        }
    }
}

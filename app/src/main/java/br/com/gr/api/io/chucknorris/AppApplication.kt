package br.com.gr.api.io.chucknorris

import android.app.Application
import br.com.gr.api.io.chucknorris.di.appModule
import br.com.gr.api.io.chucknorris.di.daoModule
import br.com.gr.api.io.chucknorris.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(appModule)
        }
    }
}
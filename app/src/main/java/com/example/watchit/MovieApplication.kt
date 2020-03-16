package com.example.watchit

import android.app.Application
import com.example.core.di.Componants.CoreComponent
import com.example.core.di.Componants.DaggerCoreComponent
import com.example.core.di.modules.AppMoule
import com.example.watchit.di.components.AppComponent
import com.example.watchit.di.components.DaggerAppComponent
import com.facebook.stetho.Stetho




class MovieApplication : Application() {

    companion object{
        lateinit var coreComponent: CoreComponent
        lateinit var appCoponent : AppComponent
    }
    override fun onCreate() {
        super.onCreate()

        coreComponent= DaggerCoreComponent.builder().appMoule(AppMoule(this)).build()
        appCoponent = DaggerAppComponent.builder().coreComponent(coreComponent).build()

        Stetho.initializeWithDefaults(this)

    }

}
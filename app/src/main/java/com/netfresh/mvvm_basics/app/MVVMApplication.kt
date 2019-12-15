package com.netfresh.mvvm_basics.app

import android.app.Application
import com.netfresh.mvvm_basics.data.db.AppDatabase
import com.netfresh.mvvm_basics.data.network.MyApi
import com.netfresh.mvvm_basics.data.network.NetworkConnectionInterceptor
import com.netfresh.mvvm_basics.data.repository.UserRepository
import com.netfresh.mvvm_basics.ui.auth.AuthViewmodelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton{ NetworkConnectionInterceptor(instance())}
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }

        bind() from provider { AuthViewmodelFactory(instance()) }
    }
}
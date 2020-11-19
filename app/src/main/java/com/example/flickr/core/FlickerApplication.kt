package com.example.flickr.core

import android.app.Application
import com.example.flickr.domain.database.FlickerDataBase
import com.example.flickr.domain.datasource.FlickerDataSource
import com.example.flickr.domain.datasource.FlickerDataSourceImpl
import com.example.flickr.domain.network.FlickerServiceGenerator
import com.example.flickr.domain.repository.FlickerRepository
import com.example.flickr.domain.repository.FlickerRepositoryImpl
import com.example.flickr.ui.viewModel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class FlickerApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FlickerApplication))

        bind() from singleton { FlickerDataBase(instance()) }
        bind() from singleton { instance<FlickerDataBase>().feedDao() }
        bind() from singleton { FlickerServiceGenerator() }
        bind<FlickerDataSource>() with singleton { FlickerDataSourceImpl(instance()) }
        bind<FlickerRepository>() with singleton {
            FlickerRepositoryImpl(instance(), instance())
        }
        bind() from provider { ViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}
package com.example.retrofitbair.presentetion.app

import android.app.Application
import com.example.retrofitbair.presentetion.api.ProductApi
import com.example.retrofitbair.presentetion.vm.PhoneViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    private val networkModule = module {
        single {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        single {
            OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }
        single {
            Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .client(get<OkHttpClient>())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

//    val vmModule = module {
//        viewModel<PhoneViewModel>{
//            PhoneViewModel()
//        }
//    }

    val apiModule = module {
        single {
            get<Retrofit>().create(ProductApi::class.java)
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            // Здесь вы добавляете модули в Koin
            modules(listOf( networkModule, apiModule) )
        }
    }
}
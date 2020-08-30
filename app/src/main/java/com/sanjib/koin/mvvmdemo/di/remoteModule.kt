package com.sanjib.koin.mvvmdemo.di

import com.sanjib.koin.mvvmdemo.api.ApiService
import com.sanjib.koin.mvvmdemo.interfaces.AppApiInterface
import org.koin.dsl.module

val remoteModule = module {

    val apiService = ApiService()

    single {
        apiService.createWebService<AppApiInterface>(
            okHttpClient = apiService.createHttpClient(),
//            baseUrl = BuildConfig.BASE_URL
            baseUrl = "http://sd2-hiring.herokuapp.com/api/"
        )
    }

}
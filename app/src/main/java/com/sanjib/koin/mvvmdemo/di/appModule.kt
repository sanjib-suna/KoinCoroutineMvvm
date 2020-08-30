package com.sanjib.koin.mvvmdemo.di


import com.sanjib.koin.mvvmdemo.viewmodel.ImageViewModel
import com.sanjib.koin.mvvmdemo.viewmodel.ListViewModel
import com.sanjib.koin.mvvmdemo.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel { UserViewModel(androidApplication(), get()) }

    viewModel { ListViewModel(androidApplication(), get())  }

    viewModel { ImageViewModel(androidApplication(), get())  }

}




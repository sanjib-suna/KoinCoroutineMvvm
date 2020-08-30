package com.sanjib.koin.mvvmdemo.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sanjib.koin.mvvmdemo.BaseViewModel
import com.sanjib.koin.mvvmdemo.interfaces.AppApiInterface
import com.sanjib.koin.mvvmdemo.model.ListApi

class ListViewModel (application: Application,
                     private  var appApiInterface: AppApiInterface
) : BaseViewModel(application) {

    var result: MutableLiveData<List<ListApi>> = MutableLiveData()

    fun apiCall(){

        launch {
            try {
                val responseBean = appApiInterface.getList().await()
                result.postValue(responseBean.body())
            } catch (e: Exception) {
            }
        }
    }
}
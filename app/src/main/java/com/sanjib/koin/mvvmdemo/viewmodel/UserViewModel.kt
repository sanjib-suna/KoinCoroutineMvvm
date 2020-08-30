package com.sanjib.koin.mvvmdemo.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.sanjib.koin.mvvmdemo.BaseViewModel
import com.sanjib.koin.mvvmdemo.interfaces.AppApiInterface
import com.sanjib.koin.mvvmdemo.model.User


class UserViewModel(application: Application,
                    private  var appApiInterface: AppApiInterface
) : BaseViewModel(application) {

    var result: MutableLiveData<User> = MutableLiveData()

    fun apiCall() {
            launch {
                try {
                    val responseBean = appApiInterface.apiData("2").await()
                    if (responseBean.isSuccessful) {
                        result.postValue(responseBean.body())
                    }
                    else{
                        Toast.makeText(getApplication(),"Error In Api",Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                }
            }
    }

}
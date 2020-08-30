package com.sanjib.koin.mvvmdemo.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.sanjib.koin.mvvmdemo.BaseViewModel
import com.sanjib.koin.mvvmdemo.interfaces.AppApiInterface
import com.sanjib.koin.mvvmdemo.model.Datas

class ImageViewModel(application: Application,
                     private  var appApiInterface: AppApiInterface
) : BaseViewModel(application) {

    var result: MutableLiveData<Datas> = MutableLiveData()

    fun apiCall() {

        val data: HashMap<String, String> = HashMap()
        data["offset"] = "1"
        data["limit"] ="10"
            launch {
                try {
                    val responseBean = appApiInterface.getList(data).await()
                    if (responseBean.status) {
                        result.postValue(responseBean.data)
                    }
                    else{
                        Toast.makeText(getApplication(),"Error In Api",Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                }
            }
    }

}
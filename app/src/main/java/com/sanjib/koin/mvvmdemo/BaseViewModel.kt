package com.sanjib.koin.mvvmdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var jobs = listOf<Job>()

    fun launch(code: suspend CoroutineScope.() -> Unit) {
        jobs = jobs + CoroutineScope(Dispatchers.IO).launch (block = code)
    }
    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
    }

}
package com.sanjib.koin.mvvmdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.sanjib.koin.mvvmdemo.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.ArrayAdapter
import com.sanjib.koin.mvvmdemo.R
import com.sanjib.koin.mvvmdemo.model.ListApi
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    private val listViewModel by viewModel<ListViewModel>()

    var heroes: Array<String ?>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)



        listViewModel.apiCall()

        listViewModel.result.observe(this, androidx.lifecycle.Observer {

            var heroListApi:List<ListApi>  = it

           heroes = arrayOfNulls<String>(heroListApi.size)

            for (i in 0 until heroListApi.size) {
                heroes!![i] = heroListApi[i].name
            }

            listViewHeroes.setAdapter(
                ArrayAdapter<String>(
                    applicationContext,
                    android.R.layout.simple_list_item_1,
                    heroes!!
                )
            )

        })

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        Log.d("Saved Data",""+heroes)

        outState.putStringArray("key", heroes)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        heroes = savedInstanceState.getStringArray("key")
    }

}

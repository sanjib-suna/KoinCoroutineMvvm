package com.sanjib.koin.mvvmdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjib.koin.mvvmdemo.ListAdapter
import com.sanjib.koin.mvvmdemo.R
import com.sanjib.koin.mvvmdemo.model.Users
import com.sanjib.koin.mvvmdemo.viewmodel.ImageViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
*
* this is designed by sanjib
*
* */

class MainActivity : AppCompatActivity() {

    var listRes = ArrayList<Users>()
    private var adapter: ListAdapter? = null

    var page:Int =1
    var limit = 10

    private var loading = true

    var pastVisiblesItems = 0
    var visibleItemCount = 0
    var  totalItemCount = 0

    var layoutManager: LinearLayoutManager? =  null

    private val imageViewModel:ImageViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()
        apiCall()
    }

    private fun apiCall(){
        imageViewModel.apiCall()
        imageViewModel.result.observe(this, Observer {list ->

            pbProgess.visibility = View.GONE

            if (page == 1) {
                listRes.clear();
            }
            if (list.has_more) {
                page++;
                loading = true;
            } else {
                loading = false;
            }
            for (i in 0 until list.users.size) {
                listRes.add(list.users[i])
            }
            adapter!!.setItems(listRes);
            adapter!!.notifyDataSetChanged();

            if (loading)
                adapter!!.showLoading(true);
            else
                adapter!!.showLoading(false);
        })
    }


    private fun setRecyclerView(){

        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        rcvMainlist.layoutManager = layoutManager
        Log.d("Adapter","adptercall"+listRes)
        adapter = ListAdapter(this,listRes)
        rcvMainlist.adapter = adapter


        rcvMainlist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    visibleItemCount = layoutManager!!.getChildCount();
                    totalItemCount = layoutManager!!.getItemCount();
                    pastVisiblesItems = layoutManager!!.findFirstVisibleItemPosition()
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {

                            apiCall()

                            adapter!!.showLoading(true);
                            loading = false;
                        }
                    } else {
                        adapter!!.showLoading(false);
                    }
                }

            }
        })
    }

}

package com.sanjib.koin.mvvmdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanjib.koin.mvvmdemo.model.Users
import kotlinx.android.synthetic.main.list_item_image.view.*


class ListAdapter (var context: Context,var mitems : List<Users> ) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var showLoading = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_image, parent, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return mitems.size
    }
    fun setItems(list: List<Users>) {
        this.mitems = list
        notifyDataSetChanged()
    }

    fun showLoading(showLoading: Boolean) {
        this.showLoading = showLoading
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {

        if (position == mitems.size - 1 && showLoading) {
            holder.lProgess.setVisibility(View.VISIBLE)
        } else {
            holder.lProgess.setVisibility(View.GONE)
        }

        Glide.with(context)
            .load(mitems[position].image)
            .into(holder.ivUser)

//        if(position %2 != 0) {
            Glide.with(context)
                .load(mitems[position].items[0])
                .into(holder.ivImageMain)
//        }
//        else{
//
//        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivUser = view.ivUser
        var lProgess = view.lProgess
        var ivImageMain = view.ivImageMain
    }
}
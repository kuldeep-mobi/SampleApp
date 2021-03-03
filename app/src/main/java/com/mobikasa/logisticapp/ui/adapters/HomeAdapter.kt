package com.mobikasa.logisticapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobikasa.logisticapp.databinding.ItemHomeRecyclerBinding
import com.mobikasa.logisticapp.models.HomeData

class HomeAdapter(private val mList: List<HomeData>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(private val homeView: ItemHomeRecyclerBinding) :
        RecyclerView.ViewHolder(homeView.root) {
        fun bindData(homeData: HomeData) {
            homeView.textName.text = homeData.name
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
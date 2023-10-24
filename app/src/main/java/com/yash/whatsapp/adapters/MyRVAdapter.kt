package com.yash.whatsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yash.whatsapp.R
import com.yash.whatsapp.databinding.ApiDataItemBinding
import com.yash.whatsapp.model.FinalModel
import com.yash.whatsapp.model.NestedRecyclerViewModel
import com.yash.whatsapp.model.RecyclerViewModel

class MyRVAdapter(private val myList: ArrayList<RecyclerViewModel>,private val inflater: LayoutInflater) : RecyclerView.Adapter<MyRVAdapter.MyViewHolder>() {

    private var nestedList:ArrayList<NestedRecyclerViewModel> = ArrayList()
    private lateinit var finalModel:FinalModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding:ApiDataItemBinding = DataBindingUtil.inflate( inflater , R.layout.api_data_item , parent, false)
        return MyViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentGrp = myList[position]
        holder.binding.tvGroupName.text = currentGrp.group_name

        holder.binding.nestedRv.visibility = if(currentGrp.isExpanded == true) View.VISIBLE else View.GONE
        holder.binding.btnSelectAll.visibility = if(currentGrp.isExpanded == true) View.VISIBLE else View.GONE

        if (currentGrp.isExpanded == true) {
            holder.binding.ivDropDown.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        }else{
            holder.binding.ivDropDown.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        }

        finalModel = FinalModel(currentGrp.order_detail_list)
        val nestedRVAdapter = MyNestedRVAdapter( finalModel , inflater)
        holder.binding.nestedRv.layoutManager = LinearLayoutManager(holder.binding.root.context)
        holder.binding.nestedRv.setHasFixedSize(true)
        holder.binding.nestedRv.adapter = nestedRVAdapter



        holder.binding.btnSelectAll.setOnClickListener {
            currentGrp.isAllItemsBtnClicked = !currentGrp.isAllItemsBtnClicked!!
            finalModel.isAllItemsDelivered = currentGrp.isAllItemsBtnClicked
            finalModel.totalItemsDelivered = if(finalModel.isAllItemsDelivered == true) finalModel.order_detail_list.size.toInt() else 0
            Toast.makeText(holder.binding.root.context , "Total Delivered Items: ${finalModel.totalItemsDelivered}" , Toast.LENGTH_LONG).show()
            holder.binding.nestedRv.adapter?.notifyDataSetChanged()
        }

        holder.binding.linearLayout.setOnClickListener {
            currentGrp.isExpanded = !currentGrp.isExpanded!!
            nestedList = currentGrp.order_detail_list
            notifyItemChanged(holder.adapterPosition)
        }
    }

    class MyViewHolder(binding: ApiDataItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding:ApiDataItemBinding = binding
    }
}
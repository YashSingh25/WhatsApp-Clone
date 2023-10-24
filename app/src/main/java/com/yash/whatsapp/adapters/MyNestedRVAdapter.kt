package com.yash.whatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColorInt
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yash.whatsapp.R
import com.yash.whatsapp.databinding.NestedListItemBinding
import com.yash.whatsapp.model.FinalModel
import com.yash.whatsapp.model.NestedRecyclerViewModel

class MyNestedRVAdapter(private var finalModel: FinalModel, private val inflater: LayoutInflater) : RecyclerView.Adapter<MyNestedRVAdapter.MyViewHolder>() {

    private var myVendorList: ArrayList<NestedRecyclerViewModel> = finalModel.order_detail_list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding: NestedListItemBinding = DataBindingUtil.inflate( inflater , R.layout.nested_list_item , parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myVendorList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentVendor = myVendorList[position]
        holder.binding.tvVendorName.text = currentVendor.vendorName
        holder.binding.ivVendor.setImageResource(currentVendor.vendorImage)
        holder.binding.tvProductName.text = currentVendor.pName
        holder.binding.tvPQntyValue.text = currentVendor.pQntyValue.toString()
        holder.binding.tvPQtyUnit.text = currentVendor.pQntyUnit
        holder.binding.tvQuantity.text = "Qty : ${currentVendor.pQnty}"

        holder.binding.btnDelivered.setOnClickListener {

        }

        holder.binding.btnNotDelivered.setOnClickListener {

        }

        if(finalModel.isAllItemsDelivered == true){

            holder.binding.btnDelivered.setTextColor("#2196F3".toColorInt())
            holder.binding.btnDelivered.background = ContextCompat.getDrawable(holder.binding.root.context , R.drawable.delivered_blue_bg)

            holder.binding.btnNotDelivered.setTextColor("#6D6F71".toColorInt())
            holder.binding.btnNotDelivered.background = ContextCompat.getDrawable(holder.binding.root.context , R.drawable.delivered_grey_bg)

        }else{

            holder.binding.btnNotDelivered.setTextColor("#2196F3".toColorInt())
            holder.binding.btnNotDelivered.background = ContextCompat.getDrawable(holder.binding.root.context , R.drawable.delivered_blue_bg)

            holder.binding.btnDelivered.setTextColor("#6D6F71".toColorInt())
            holder.binding.btnDelivered.background = ContextCompat.getDrawable(holder.binding.root.context , R.drawable.delivered_grey_bg)

        }
    }


    class MyViewHolder(binding: NestedListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: NestedListItemBinding = binding
    }
}
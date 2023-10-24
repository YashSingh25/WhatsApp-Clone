package com.yash.whatsapp.model

data class FinalModel(
    val order_detail_list:ArrayList<NestedRecyclerViewModel>,
    var isAllItemsDelivered:Boolean ?= false,
    var totalItemsDelivered:Int ?= 0
)
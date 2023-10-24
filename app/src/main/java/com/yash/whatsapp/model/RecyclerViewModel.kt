package com.yash.whatsapp.model

data class RecyclerViewModel (
    val group_id:Int,
    val group_name:String,
    val order_detail_list:ArrayList<NestedRecyclerViewModel>,
    var isExpanded:Boolean ?= false,
    var isAllItemsBtnClicked:Boolean ?= false

)
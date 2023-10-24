package com.yash.whatsapp.model

data class GroupData(
    val first_name: String,
    val last_name: String,
    val name: String,
    val online_for_shop: String,
    val order_detail: List<OrderDetail>,
    val user_id: String,
    val user_name: String
)
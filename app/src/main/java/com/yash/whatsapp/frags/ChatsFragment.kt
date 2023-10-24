package com.yash.whatsapp.frags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.whatsapp.R
import com.yash.whatsapp.adapters.MyRVAdapter
import com.yash.whatsapp.databinding.FragmentChatsBinding
import com.yash.whatsapp.model.*
import com.yash.whatsapp.retrofit.APIService
import com.yash.whatsapp.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ChatsFragment : Fragment() {

    private lateinit var _binding: FragmentChatsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater , R.layout.fragment_chats , container , false)

        //
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(context)

        val apiService = RetrofitInstance.getRetrofitInstance().create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val apiData: Response<APIData> = apiService.getData()
            Log.d("data", "${apiData.body()?.data}")

            val rvList:ArrayList<RecyclerViewModel> = ArrayList()

            val data = apiData.body()?.data

            var group_id:Int
            var group_name:String
            var order_detail_list:ArrayList<NestedRecyclerViewModel> = ArrayList<NestedRecyclerViewModel>()

            if(data!=null){
                for (d in data){
                    group_id = d.group_id.toInt()
                    group_name = d.group_name

                    Log.e("gd size", d.group_data.size.toString() )

                    for (gd in d.group_data){

                        for (odl in gd.order_detail){
                            order_detail_list.add(NestedRecyclerViewModel(odl.full_name , R.drawable.milk_img , odl.product_name , odl.quantity.toInt() , odl.product_qty.toInt() , odl.unit_name ))

                        }


                    }
                    Log.e("odl size", order_detail_list.size.toString() )
                    Log.e("my list", order_detail_list.toString())
                    rvList.add(RecyclerViewModel(group_id , group_name , order_detail_list))
                    order_detail_list = ArrayList()

                }
                Log.e("my rv list", rvList.toString())

                withContext(Dispatchers.Main){
                    binding.rv.adapter = MyRVAdapter(rvList , inflater)
                }

            }else{
                withContext(Dispatchers.Main){
                    Toast.makeText(context , "empty data" , Toast.LENGTH_SHORT).show()
                }

            }

        }

        return binding.root
    }

}

//
//            var group_data:ArrayList<GroupData> = ArrayList()
//
//
//
//            //

//            var user_id:Int
//            var name:String
//            var online_for_shop:String
//            var order_detail:Int
//            //
//
//
//            if (data != null) {
//                for(d in data){
//                    group_id = d.group_id.toInt()
//                    group_name = d.group_name
//                    group_data = d.group_data as ArrayList<GroupData>
//                    for(gd in group_data){
//                        user_id = gd.user_id.toInt()
//                        name = gd.name
//                        online_for_shop = gd.online_for_shop
//                        order_detail = gd.order_detail.size
//                        val rvObject = RecyclerViewModel(group_id , group_name,user_id,name,online_for_shop,order_detail)
//                        rvList.add(rvObject)
//                    }
//                }
//
//
//
//            }
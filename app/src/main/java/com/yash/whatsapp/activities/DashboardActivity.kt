package com.yash.whatsapp.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.yash.whatsapp.R
import com.yash.whatsapp.activities.adapters.ViewPagerAdapter
import com.yash.whatsapp.frags.CallsFragment
import com.yash.whatsapp.frags.ChatsFragment
import com.yash.whatsapp.frags.UpdatesFragment
import com.yash.whatsapp.model.APIData
import com.yash.whatsapp.model.Data
import com.yash.whatsapp.model.GroupData
import com.yash.whatsapp.model.RecyclerViewModel
import com.yash.whatsapp.retrofit.APIService
import com.yash.whatsapp.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        // Create an adapter to manage the fragments
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // Add your Fragments to the adapter
        adapter.addFragment(ChatsFragment(), "Chats")
        adapter.addFragment(UpdatesFragment(), "Updates")
        adapter.addFragment(CallsFragment(), "Calls")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        //

        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isUserLogin", true)
        editor.apply()

    }
}
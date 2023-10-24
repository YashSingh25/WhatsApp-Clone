package com.yash.whatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yash.whatsapp.R
import com.yash.whatsapp.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicyActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPrivacyPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_privacy_policy)

        val receivedLang = intent.getStringExtra("selectedOp")

        binding.apply {
            tvLang.text = receivedLang

            button.setOnClickListener {
                val intent = Intent(this@PrivacyPolicyActivity , EnterPhoneNumberActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
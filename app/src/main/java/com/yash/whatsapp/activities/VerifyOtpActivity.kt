package com.yash.whatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.yash.whatsapp.R
import com.yash.whatsapp.databinding.ActivityVerifyOtpBinding

class VerifyOtpActivity : AppCompatActivity() {

    private lateinit var binding:ActivityVerifyOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_verify_otp)

        val phoneNo = intent.getStringExtra("phoneNo")

        binding.apply {

            tvPhoneNo.text = "+91 $phoneNo"

            button.setOnClickListener {
                if(editTextPhone.text.isNotEmpty()){
                    if(editTextPhone.text.length == 6){

                        val intent = Intent(this@VerifyOtpActivity  , DashboardActivity::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(applicationContext , "Please enter a valid 6 digit code!", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(applicationContext , "Please enter code!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
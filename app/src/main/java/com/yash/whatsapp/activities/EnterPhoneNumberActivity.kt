package com.yash.whatsapp.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.yash.whatsapp.R
import com.yash.whatsapp.databinding.ActivityEnterPhoneNumberBinding

class EnterPhoneNumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnterPhoneNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_enter_phone_number)

        binding.apply {

            button.setOnClickListener {
                if(etPhoneNo.text.isNotEmpty()){
                    if(etPhoneNo.text.length == 10){

                        val alertDialog = AlertDialog.Builder(this@EnterPhoneNumberActivity)
                            .setTitle("You entered the Phone Number:")
                            .setMessage("\n+91 ${etPhoneNo.text} \n\nIs this OK,or would you like to edit the\nnumber")
                            .setPositiveButton("OK") { dialog, which ->
                                // Handle the "OK" button click
                                Toast.makeText(applicationContext, "Phone number confirmed!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@EnterPhoneNumberActivity , VerifyOtpActivity::class.java)
                                intent.putExtra("phoneNo" , etPhoneNo.text.toString())
                                startActivity(intent)
                            }
                            .setNegativeButton("Edit") { dialog, which ->
                                // Handle the "Edit" button click
                                // Add code here to allow the user to edit the phone number
                                dialog.dismiss()
                            }
                            .create()

                            alertDialog.show()

                    }else{
                        Toast.makeText(applicationContext , "Please enter a valid 10 digit Number!", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(applicationContext , "Please enter Phone Number!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
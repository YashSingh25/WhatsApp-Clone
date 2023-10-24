package com.yash.whatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.yash.whatsapp.R
import com.yash.whatsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    var isAnyOpSelected:Boolean = false
    private lateinit var selectedOp : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            // Set a listener for the RadioGroup to handle RadioButton selection changes
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId != View.NO_ID && checkedId != null) {
                    // A RadioButton has been selected
                    isAnyOpSelected = true

                    val checkedRadioButton = findViewById<RadioButton>(checkedId)

                    checkedRadioButton?.let {
                        // Do something with the checked RadioButton
                        isAnyOpSelected = true
                        val selectedOption = checkedRadioButton.id.toString()
                        selectedOp = selectedOption

                        // Example: Display the selected option
                        Toast.makeText(applicationContext, "Selected: $selectedOption", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // No RadioButton is selected
                    isAnyOpSelected = false
                }
            }

            fabNext.setOnClickListener {
                if(isAnyOpSelected){
                    val intent = Intent(this@MainActivity , PrivacyPolicyActivity::class.java)
                    intent.putExtra("selectedOp" , selectedOp.substring(2 , selectedOp.length-1))
                    startActivity(intent)
                }else{
                    val intent = Intent(this@MainActivity , PrivacyPolicyActivity::class.java)
                    intent.putExtra("selectedOp" , "English")
                    startActivity(intent)
                }
            }


        }
    }
}
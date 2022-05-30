package com.sparrow.starbuckscoffee

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val PERMISSION_CODE = 100
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            val permission = arrayOf(Manifest.permission.SEND_SMS)
            requestPermissions(permission, PERMISSION_CODE)
        }

        val send_btn = findViewById<Button>(R.id.btn_sendno)
        send_btn.setOnClickListener {
            sendMessageToStarbucks()
        }

    }

    private fun sendMessageToStarbucks() {
        val phonenumber = findViewById<EditText>(R.id.et_mobile)
        val issue = findViewById<EditText>(R.id.et_content)
        val cust_no: String = phonenumber.text.toString()
        val msg: String = issue.text.toString()
        if (cust_no.isEmpty() || msg.isEmpty()) {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(cust_no, null, msg, null, null)
                Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
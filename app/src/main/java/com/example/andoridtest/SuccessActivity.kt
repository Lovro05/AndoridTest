package com.example.andoridtest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val text = findViewById<TextView>(R.id.successText)
        val button = findViewById<Button>(R.id.buttonIntent)

        val ime = intent.getStringExtra("name")
        text.text = "$ime uspješno ste napravili 10 koraka"

        button.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:02345141212345")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", text.text)
            startActivity(intent)
        }
    }
}
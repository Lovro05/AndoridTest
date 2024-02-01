package com.example.andoridtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.textView)

    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.i("LogStart", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
        Log.i("ResumeLog", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
        Log.i("PauseLog", "onPause")

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
        Log.i("StopLog", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.i("RestartLog", "onRestart")
    }

    fun setOnClickListenerUp() {
        text.text = ((text.text.toString().toInt() + 1).toString())


    }
    fun setOnClickListenerDown() {

        if (text.text.toString().toInt() > 0) {
            text.text = (((text.text.toString().toInt() -1).toString()))
        }
    }

    override fun onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("count", text)
    }

    override fun onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState)
        text = savedInstanceState.getInt("count")

    }
}
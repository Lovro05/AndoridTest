package com.example.andoridtest

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var textCount: TextView
    private lateinit var sharedPref : SharedPreferences
    private lateinit var button : Button
    companion object {
        const val COUNT_KEY = "COUNT_KEY"
    }

    private var counter = 0
        set(value) {
            field = value
            textCount.text = value.toString()
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.buttonDown)
        textCount = findViewById<View>(R.id.textView) as TextView
        sharedPref = getPreferences(MODE_PRIVATE)
        sharedPref.getInt("count", counter)
        Log.i("MyLog", "valOnStart $counter")
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStart")

    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onResume")

    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onRestart")

    }
    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onPause")
        sharedPref.edit().putInt("count", counter).apply()
        Log.i("MyLog", "valOnStop $counter")
        val numberSharedPref = 66
        sharedPref.getInt("count", numberSharedPref)
        Log.i("MyLog", "valOnStopRet $numberSharedPref")

    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStop")

    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onDestroy")

    }
    fun setOnClickListenerUp(view: View) {
        counter++
        textCount.text = counter.toString()
        if (counter == 10)
        {
            val intent = Intent(this, SuccessActivity::class.java).apply {
                putExtra("name", findViewById<TextView>(R.id.plainTextName).text.toString())
            }
            startActivity(intent)

            button.isClickable = false
            button.setBackgroundColor(Color.RED)

        } else if (counter == 11) {
            counter = 0
            button.isClickable = true
            button.setBackgroundColor(Color.MAGENTA)
        }

        textCount.text = counter.toString()
    }
    fun setOnClickListenerDown(view: View) {
        if(counter > 0) {
            counter--
            textCount.text = counter.toString()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("MyLog", "onSaveInstanceState")

        outState.putInt(COUNT_KEY, counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MyLog", "onRestoreInstanceState")

        counter = savedInstanceState.getInt(COUNT_KEY)
    }
}
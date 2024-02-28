package com.example.andoridtest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
import android.os.LocaleList
import java.util.*
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var textCount: TextView
    private lateinit var sharedPref : SharedPreferences
    private lateinit var button : Button
    private lateinit var textContextMenu: TextView

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
        setSupportActionBar(findViewById(R.id.myToolbar))
        button = findViewById(R.id.buttonDown)
        textCount = findViewById<View>(R.id.textView) as TextView
        sharedPref = getPreferences(MODE_PRIVATE)
        sharedPref.getInt("count", counter)
        textContextMenu = findViewById(R.id.textViewContextMenu)
        registerForContextMenu(textContextMenu);
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
            button.setBackgroundColor(Color.rgb(255,165,0))
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources: Resources = context.resources
        val config: Configuration = resources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            config.setLocales(localeList)

        } else {
            config.locale = locale
        }

        resources.updateConfiguration(config, resources.displayMetrics)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.restore_counter -> {
                counter = 0
                button.setBackgroundColor(Color.rgb(255,165,0))
                true
            }
            R.id.croatian -> {
                setLocale(context = this, languageCode = "hr")
                recreate() // Recreate the activity to apply the language change
                return true
            }

            R.id.english -> {
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val spannableString = SpannableString("Counter = $counter")

        val colorSpan = ForegroundColorSpan(Color.RED)

        // Apply the ForegroundColorSpan to the entire length of the SpannableString
        spannableString.setSpan(
            colorSpan,
            0,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // SpannableString -> HeaderTitle Context menu (kontekstualnog izbornika)
        menu.setHeaderTitle(spannableString)
        menu.add(0, v.id, 0, "Reset")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.title === "Reset") {
            counter = 0
        }
        return true
    }
}
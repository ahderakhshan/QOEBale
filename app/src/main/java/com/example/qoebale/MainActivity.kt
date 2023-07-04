package com.example.qoebale

import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOError

class MainActivity : AppCompatActivity() {
    private val baleApiClient = BaleApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val sendphotobutton = findViewById<Button>(R.id.button2)
        val checkbox = findViewById<CheckBox>(R.id.checkBox)
        val text = findViewById<TextView>(R.id.textView)
        button.setOnClickListener {
            checkbox.isChecked = ! checkbox.isChecked
            GlobalScope.launch(Dispatchers.Main) {
                val a = sendBaleMessage()
                text.text = a.toString()
            }

        }
        sendphotobutton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                sendBalePhoto()

            }
        }
    }

    suspend fun sendBaleMessage(): Float {
        val a = baleApiClient.sendMessage("@mabahesv2", "salam")
        return a
    }
    suspend fun sendBalePhoto(){
        baleApiClient.SendPhoto()
    }
}

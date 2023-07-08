package com.example.qoebale

import android.content.Context
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    private val baleApiClient = BaleApiClient()
    var text_send_speed = 0.0
    var photo_send_speed = 0.0
    var audio_send_speed = 0.0
    var doc_send_speed = 0.0
    var video_send_speed = 0.0
    var get_admininfo_speed = 0.0
    var get_fileinfo_speed = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startqoebutton = findViewById<Button>(R.id.button4)
        val text = findViewById<TextView>(R.id.textView2)
        text.text = "click startqoe button to get results"
        startqoebutton.setOnClickListener {
            text.text = "QOE started \n" +
                    "waiting for results"
            GlobalScope.launch(Dispatchers.Main) {
                    text_send_speed = sendBaleMessage()
                    photo_send_speed = sendBalePhoto()
                    doc_send_speed = sendBaleDoc()
                    audio_send_speed = sendBaleAudio()
                    video_send_speed = sendBaleVideo()
                    text.text = "text send speed : $text_send_speed \n" +
                        "photo send speed :  $photo_send_speed \n" +
                        "doc send speed : $doc_send_speed \n" +
                        "audio_send_speed : $audio_send_speed\n " +
                        "video send speed : $video_send_speed\n " +
                        "get fileinfo speed : $get_fileinfo_speed\n" +
                        "get admin info speed : $get_admininfo_speed\n" +
                        "all of this numbers are byte per seconds"

            }
            GlobalScope.launch(Dispatchers.IO) {
                    get_fileinfo_speed = getFileInfo()
                    get_admininfo_speed = getAdminInfo()

            }
        }
    }


    suspend fun sendBaleMessage(): Double {
        val result = baleApiClient.sendMessage("@mabahesv2", "salam")
        return result
    }
    suspend fun sendBalePhoto(): Double{
        val result = baleApiClient.SendPhoto()
        return result
    }
    suspend fun sendBaleAudio(): Double {
        val result = baleApiClient.sendAudio()
        return result
    }
    suspend fun sendBaleDoc(): Double {
        val result = baleApiClient.sendDoc()
        return result
    }
    suspend fun sendBaleVideo(): Double {
        val result = baleApiClient.sendVideo()
        return result
    }
    suspend fun getAdminInfo(): Double {
        val result = baleApiClient.getAdminsInfo()
        return result
    }
    suspend fun getFileInfo(): Double {
        return baleApiClient.getFileInfo()
    }

}

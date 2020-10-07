package com.dd.preparationforaadexam

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


//Implement TextToSpeech.OnInitListener
class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create and initialize TextToSpeech object
        val tts = TextToSpeech(applicationContext, this)

        //do action on button click
        btnClick.setOnClickListener {

            //set Language type
            tts.language = Locale.ENGLISH

            //Run speak(...) function with required parameters
            tts.speak("hello, this is text to speech", TextToSpeech.QUEUE_FLUSH, null, "speech")
        }
    }

    //override onInit function
    override fun onInit(status: Int) {
    }
}
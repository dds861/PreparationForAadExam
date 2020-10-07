package com.dd.preparationforaadexam

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


//Implement TextToSpeech.OnInitListener
class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private var ttsInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create and initialize TextToSpeech object
        tts = TextToSpeech(applicationContext, this)

        //do action on button click
        btnClick.setOnClickListener {

            //Check our boolean if TextToSpeech is initialized
            if (!ttsInitialized) {
                Log.i("autolog", "TextToSpeech wasn't initialized: ");
            } else {

                //Run speak(...) function with required parameters
                tts.speak("hello, this is text to speech", TextToSpeech.QUEUE_FLUSH, null, "speech")
            }

        }
    }

    //override onInit function
    override fun onInit(status: Int) {

        //Check TextToSpeech
        if (status == TextToSpeech.SUCCESS) {

            //Choose Language
            val result: Int = tts.setLanguage(Locale.US)

            //Check language is supported
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.i("autolog", "This language isn't supported: ");
            } else {

                //Set boolean that TextToSpeech is initialized
                ttsInitialized = true
            }
        } else {
            Log.i("autolog", "TTS initialization failed: ");
        }
    }
}
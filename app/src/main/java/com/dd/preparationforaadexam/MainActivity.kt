package com.dd.preparationforaadexam

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClick.setOnClickListener {
            //5. Create inner class MyResultReceiver(...) object
            val receiver = MyResultReceiver(Handler())

            //6. And pass that receiver to the static function
            MyIntentService.startActionFoo(this, "param1", receiver)
        }
    }

    //1. Create class with ResultReceiver implementation
    inner class MyResultReceiver(handler: Handler) : ResultReceiver(handler) {

        //2. override the onReceiveResult function
        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {

            //3. check for resultCode
            if (resultCode == Activity.RESULT_OK) {

                //4. Set String to TextView, which is received by key from Bundle
                tvText.text = resultData?.getString(FILE_CONTENTS_KEY)
            }
        }
    }
}
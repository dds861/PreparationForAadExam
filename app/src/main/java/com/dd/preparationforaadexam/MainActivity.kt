package com.dd.preparationforaadexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set text to TextView ny passing *.txt name
        tvText.text = readFileFromAssets("text_file.txt")
    }

    private fun readFileFromAssets(filename: String): String {

        //We will build the string line by line from *.txt file
        val builder = StringBuilder()

        //BufferedReader is needed to read the *.txt file
        //Create and Initialize BufferedReader
        val reader = BufferedReader(InputStreamReader(assets.open(filename)))

        //This variable will contain the text
        var line: String?

        //check if there is a more line available
        while (reader.readLine().also { line = it } != null) {
            builder.append(line).append("\n")
        }

        //Need to close the BufferedReader
        reader.close()

        //just return the String of the *.txt file
        return builder.toString()
    }
}
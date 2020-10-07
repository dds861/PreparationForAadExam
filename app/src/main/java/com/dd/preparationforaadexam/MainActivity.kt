package com.dd.preparationforaadexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set text to TextView from parsed Json
        tvText.text = getParsedJson()
    }

    private fun getParsedJson(): String {

        //get file name
        val json: String = readFileFromAssets("data.json")

        //Variable to return
        val parsedText = mutableListOf<String>()

        //Create JSONArray object, which will parse the data
        val jsonArray = JSONArray(json)

        //iterate through the parsed data
        for (i in 0 until jsonArray.length()) {

            //get one item from parsed json
            val item = jsonArray[i] as JSONObject

            //from data.json we will get "name" and "price"
            val itemName = item.getString("name")
            val itemPrice = item.getDouble("price")

            //Create StringBuilder to build the string
            val builder = java.lang.StringBuilder(itemName)
                .append(" (")
                .append(itemPrice)
                .append(")\n")

            //add every string to list
            parsedText.add(i, builder.toString())
        }

        //return parsed list as a String
        return parsedText.toString()
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
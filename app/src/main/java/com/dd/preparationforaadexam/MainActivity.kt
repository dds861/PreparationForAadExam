package com.dd.preparationforaadexam

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customDialog = CustomDialogFragment()
        customDialog.show(supportFragmentManager, "CustomDialogFragment")
    }
}

class CustomDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = androidx.appcompat.app.AlertDialog.Builder(activity!!)

        // Create the custom layout using the LayoutInflater class
        val inflater = activity!!.layoutInflater
        val v = inflater.inflate(R.layout.custom_dialog_layout, null)

        // Build the dialog
        builder.setTitle("Please enter your name")
            .setPositiveButton("OK") { dialog, which -> Log.i("autolog", "OK Clicked") }
            .setNegativeButton("Cancel") { dialog, which -> Log.i("autolog", "Cancel clicked") }
            .setView(v)

        return builder.create()
    }
}

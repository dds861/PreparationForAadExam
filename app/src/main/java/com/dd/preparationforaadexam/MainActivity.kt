package com.dd.preparationforaadexam

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val simpleDialog = SimpleDialogFragment()
        simpleDialog.show(supportFragmentManager, "SimpleDialogFragment")
    }

    class SimpleDialogFragment : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity!!)

            builder.setTitle("My Title")
            builder.setMessage("My message")
            builder.setPositiveButton("Yes") { dialog, which ->}
            builder.setNegativeButton("No") { dialog, which ->}
            builder.setNeutralButton("Neutral") { dialog, which ->}
            return builder.create()
        }
    }
}

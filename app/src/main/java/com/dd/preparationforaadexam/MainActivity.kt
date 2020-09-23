package com.dd.preparationforaadexam

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val simpleDialog = SimpleDialogFragment()
//        simpleDialog.show(supportFragmentManager, "SimpleDialogFragment")

        val complexDialog = SingleChoiceDialogFragment()
        complexDialog.show(supportFragmentManager, "SingleChoiceDialogFragment")
    }

    class SimpleDialogFragment : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity!!)

            builder.setTitle("My Title")
            builder.setMessage("My message")
            builder.setPositiveButton("Yes") { dialog, which -> }
            builder.setNegativeButton("No") { dialog, which -> }
            builder.setNeutralButton("Neutral") { dialog, which -> }
            return builder.create()
        }
    }

    class SingleChoiceDialogFragment : DialogFragment() {
        private val colors = arrayOf("Red", "Blue", "Green", "Yellow")

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("My title")

            builder.setItems(colors) { dialog, which ->Log.i("autolog", "colors: " + colors[which])}
            return builder.create()
        }
    }
}

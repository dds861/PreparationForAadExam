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

//        val complexDialog = SingleChoiceDialogFragment()
//        complexDialog.show(supportFragmentManager, "SingleChoiceDialogFragment")

        showDatePickerDialog()
    }

    private fun showDatePickerDialog(){
        // Get a calendar instance
        val cal = Calendar.getInstance()

        // Create a DatePickerDialog
        val datePicker = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                Log.i("autolog", String.format("Date Chosen -- day: %d, month: %d, year: %d", dayOfMonth, monthOfYear, year))
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )

        // Set the title and show the dialog
        datePicker.setTitle("Choose a Date")
        datePicker.show()
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

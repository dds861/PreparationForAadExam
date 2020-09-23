package com.dd.preparationforaadexam

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity(), CustomDialogFragment.CustomDialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customDialog = CustomDialogFragment()
        customDialog.show(supportFragmentManager, "CustomDialogFragment")
    }

    override fun onPositiveResult(dlg: DialogFragment, text: String) {
        Log.i("autolog", "onPositiveResult:$text");
    }

    override fun onNegativeResult(dlg: DialogFragment) {
        Log.i("autolog", "onNegativeResult:");
    }
}

class CustomDialogFragment : DialogFragment() {
    private var mHost: CustomDialogListener? = null

    interface CustomDialogListener {
        fun onPositiveResult(dlg: DialogFragment, text: String)
        fun onNegativeResult(dlg: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = androidx.appcompat.app.AlertDialog.Builder(activity!!)

        // Create the custom layout using the LayoutInflater class
        val inflater = activity!!.layoutInflater
        val v = inflater.inflate(R.layout.custom_dialog_layout, null)

        val firstName = v.findViewById<EditText>(R.id.edtFirstName)
        val lastName = v.findViewById<EditText>(R.id.edtLastName)

        // Build the dialog
        builder.setTitle("Please enter your name")
            .setPositiveButton("OK") { dialog, which ->
                Log.i("autolog", "OK Clicked")
                mHost!!.onPositiveResult(this@CustomDialogFragment, "${firstName.text} ${lastName.text}")
            }
            .setNegativeButton("Cancel") { dialog, which ->
                Log.i("autolog", "Cancel clicked")
                mHost!!.onNegativeResult(this@CustomDialogFragment)
            }
            .setView(v)

        return builder.create()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mHost = context as CustomDialogListener
    }
}

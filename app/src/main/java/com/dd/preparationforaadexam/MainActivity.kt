package com.dd.preparationforaadexam

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showActionSnackbar()
    }

    private fun showToast() {
        val toast = Toast.makeText(this, "This is a toast", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }


    private fun showCustomToast(){
        // Get the custom layout and inflate it
        val inflater = LayoutInflater.from(this)
        val layout = inflater.inflate(R.layout.custom_toast_layout, findViewById(R.id.customToastLayout))

        // Build a toast message that uses the custom layout
        val tv = layout.findViewById<View>(R.id.textContent) as TextView
        tv.text = "This is a custom toast"
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.BOTTOM or Gravity.START, 100, 100)
        toast.view = layout
        toast.show()
    }

    private fun showSnackbar() {
        // Create and show the snackbar
        val snackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
            "This is a snackbar", Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun showActionSnackbar() {
        // Create a snackbar that has an action item
        val snackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
            "This is a snackbar", Snackbar.LENGTH_LONG)
        snackbar.setAction("Very Cool") {
            Toast.makeText(applicationContext, "Snackbar Action Click!",
                Toast.LENGTH_SHORT).show()
        }
        snackbar.setActionTextColor(Color.RED)
        snackbar.show()
    }

}
package com.dd.preparationforaadexam

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnStart.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.more_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu1 -> Toast.makeText(this, "menu1", Toast.LENGTH_SHORT).show()
                    R.id.menu2 -> Toast.makeText(this, "menu2", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show()
                }
                return@setOnMenuItemClickListener false
            }

            popupMenu.show()
        }
    }
}
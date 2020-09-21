package com.dd.preparationforaadexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myDao = MyDatabase.getDatabase(applicationContext).myDao()

        CoroutineScope(Dispatchers.IO).launch {
            val data = myDao.getAll()
            if (data.isEmpty()) {
                myDao.deleteAll()
                myDao.insertMyDataClass(getList())
            }
        }
    }

    private fun getList(): List<MyDataClass> {

        val arrayList = ArrayList<MyDataClass>()
        arrayList.add(
            MyDataClass(
                tableId = 0,
                name = "a",
                description = "b"
            )
        )
        return arrayList
    }
}
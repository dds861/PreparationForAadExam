package com.dd.preparationforaadexam.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.preparationforaadexam.WordsApplication
import com.dd.preparationforaadexam.data.Word
import com.dd.preparationforaadexam.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity1"

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            wordViewModel.words.collectLatest {
                adapter.submitData(it)
            }
        }

        wordViewModel.getWord(2).observe(this, Observer { Log.i(TAG, "onCreate: $it") })

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            wordViewModel.insert(Word(word = "any text"))
        }
    }
}
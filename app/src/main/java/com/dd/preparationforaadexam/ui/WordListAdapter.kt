package com.dd.preparationforaadexam.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dd.preparationforaadexam.data.Word

class WordListAdapter : RecyclerView.Adapter<WordViewHolder>() {

    private var words = emptyList<Word>() // Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        words[position].let { holder.bind(it) }
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}
package com.dd.preparationforaadexam.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dd.preparationforaadexam.R
import com.dd.preparationforaadexam.data.Word


class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val wordItemView: TextView = itemView.findViewById(R.id.textView)

    companion object {
        fun create(parent: ViewGroup): WordViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return WordViewHolder(view)
        }
    }

    fun bind(current: Word) {
        wordItemView.text = current.word
    }
}
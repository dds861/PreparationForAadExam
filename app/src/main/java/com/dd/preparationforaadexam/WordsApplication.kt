package com.dd.preparationforaadexam

import android.app.Application
import com.dd.preparationforaadexam.data.WordDatabase
import com.dd.preparationforaadexam.data.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { WordDatabase.getInstance(this, applicationScope) }
    val repository by lazy { WordRepository(database.getDao()) }
}

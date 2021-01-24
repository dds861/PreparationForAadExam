package com.dd.preparationforaadexam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getDao(): WordDao

    companion object {
        private var INSTANCE: WordDatabase? = null

        fun getInstance(context: Context, coroutineScope: CoroutineScope): WordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordDatabase::class.java,
                        "myDatabase"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(WordDatabaseCallback(coroutineScope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class WordDatabaseCallback(
            private val coroutineScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                coroutineScope.launch {
                    populateDatabase(database.getDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()
            
            for (i in 0..200) {
                val word = Word(word = "Number: $i")
                wordDao.insert(word)
            }
        }
    }
}
package com.tahirdev.mvvmretrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tahirdev.mvvmretrofit.models.Result


@Database(entities = [Result::class], version = 1)
abstract class QDatabase() : RoomDatabase() {

    abstract fun quoteDAO() : QuoteDAO

    companion object {
        @Volatile
        private var INSTANCE : QDatabase? = null

        fun getDatabase(context: Context) : QDatabase {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext
                        ,QDatabase::class.java,
                        "quoteDB").build()
                }
            }
            return INSTANCE!!

        }

    }

}
package com.lucasprojects.braziliancovid19.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucasprojects.braziliancovid19.model.dao.DataDAO
import com.lucasprojects.braziliancovid19.model.domain.data.Data

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class BrazilianAppDatabase : RoomDatabase() {

    abstract fun getDataDAO(): DataDAO

    companion object {
        @Volatile
        private var INSTANCE: BrazilianAppDatabase? = null

        fun getInstance(ctx: Context): BrazilianAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(ctx, BrazilianAppDatabase::class.java, "BrazilianCovidDB").build()
                INSTANCE = db
                db
            }
        }
    }
}
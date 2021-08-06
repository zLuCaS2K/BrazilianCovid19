package com.lucasprojects.braziliancovid19.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucasprojects.braziliancovid19.model.domain.data.Data

@Dao
interface DataDAO {

    @Query("SELECT * FROM data ORDER BY data.name ASC")
    fun getAllData(): LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(listData: List<Data>)

}
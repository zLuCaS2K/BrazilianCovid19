package com.lucasprojects.braziliancovid19.model.repository

import com.lucasprojects.braziliancovid19.model.dao.DataDAO
import com.lucasprojects.braziliancovid19.model.domain.data.Data

class DataRepository private constructor(private val _dataDAO: DataDAO) {

    fun getAllData() = _dataDAO.getAllData()

    suspend fun insertAllData(listData: List<Data>) {
        _dataDAO.insertAllData(listData)
    }

    companion object {
        fun create(dataDAO: DataDAO): DataRepository {
            return DataRepository(dataDAO)
        }
    }
}
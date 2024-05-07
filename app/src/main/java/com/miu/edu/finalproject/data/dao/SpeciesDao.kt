package com.miu.edu.finalproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miu.edu.finalproject.data.model.Animal
import com.miu.edu.finalproject.data.model.Species
import com.miu.edu.finalproject.utils.BaseDao

@Dao
interface SpeciesDao : BaseDao<Species> {

    @Query("SELECT * FROM species")
    fun getAllSpecies() : LiveData<List<Species>>

    @Query("DELETE FROM species")
    suspend fun deleteAllSpecies()
}
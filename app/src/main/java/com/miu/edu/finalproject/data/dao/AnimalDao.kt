package com.miu.edu.finalproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miu.edu.finalproject.data.model.Animal
import com.miu.edu.finalproject.utils.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao : BaseDao<Animal> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(animal: Animal)

    @Query("SELECT * FROM animals")
    fun getAllAnimals() : LiveData<List<Animal>>

    @Query("DELETE FROM animals")
    suspend fun deleteAllAnimals()
}
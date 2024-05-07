package com.miu.edu.finalproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.miu.edu.finalproject.data.dao.AnimalDao
import com.miu.edu.finalproject.data.dao.SpeciesDao
import com.miu.edu.finalproject.data.model.Animal
import com.miu.edu.finalproject.data.model.Species
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Animal::class, Species::class], version = 1, exportSchema = false)
abstract class AnimalKingdomDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao
    abstract fun speciesDao(): SpeciesDao

    companion object {
        private var INSTANCE: AnimalKingdomDatabase? = null

        fun getDatabase(context: Context): AnimalKingdomDatabase =
            INSTANCE?: synchronized(AnimalKingdomDatabase::class) {
                INSTANCE?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AnimalKingdomDatabase =
            Room.databaseBuilder(
                context,
                AnimalKingdomDatabase::class.java,
                "animal_kingdom_db"
            ).fallbackToDestructiveMigration().build()
    }
}
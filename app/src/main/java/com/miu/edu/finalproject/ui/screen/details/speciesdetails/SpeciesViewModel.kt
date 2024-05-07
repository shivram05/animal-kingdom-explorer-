package com.miu.edu.finalproject.ui.screen.details.speciesdetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miu.edu.finalproject.data.model.Species
import com.miu.edu.finalproject.data.repository.SpeciesRepository

class SpeciesViewModel  : ViewModel(){

    var speciesListLiveData: LiveData<List<Species>>? = null

    fun insertData(context: Context, species: Species) {
        SpeciesRepository.insertData(context,species)
    }

    fun getSpeciesList(context: Context): LiveData<List<Species>>? {
        speciesListLiveData = SpeciesRepository.getSpeciesDataList(context)
        return speciesListLiveData
    }

}
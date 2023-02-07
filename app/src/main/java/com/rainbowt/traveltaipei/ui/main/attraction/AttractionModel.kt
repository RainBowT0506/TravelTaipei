package com.rainbowt.traveltaipei.ui.main.attraction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainbowt.traveltaipei.data.repository.AttractionsRepository
import com.rainbowt.traveltaipei.data.model.Attraction
import kotlinx.coroutines.launch

class AttractionModel(private val repository: AttractionsRepository) : ViewModel() {
    private val _attraction: MutableLiveData<Attraction> = MutableLiveData()
    val attraction: LiveData<Attraction> get() = _attraction
    fun getAttractions() = viewModelScope.launch {
        _attraction.value = repository.getAttractionsApi()
    }
}
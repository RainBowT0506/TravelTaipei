package com.rainbowt.traveltaipei.ui.main.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rainbowt.traveltaipei.data.model.Detail
import com.rainbowt.traveltaipei.data.repository.AttractionsRepository

class DetailModel(private val repository: AttractionsRepository): ViewModel() {
    var detail = MutableLiveData<Detail>(null)

}
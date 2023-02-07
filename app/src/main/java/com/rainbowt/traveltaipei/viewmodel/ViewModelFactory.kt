package com.rainbowt.traveltaipei.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rainbowt.traveltaipei.data.repository.AttractionsRepository
import com.rainbowt.traveltaipei.data.repository.BaseRepository
import com.rainbowt.traveltaipei.ui.main.attraction.AttractionModel
import com.rainbowt.traveltaipei.ui.main.detail.DetailModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when{

            modelClass.isAssignableFrom(AttractionModel::class.java) -> AttractionModel(
                repository as AttractionsRepository
            ) as T

            modelClass.isAssignableFrom(DetailModel::class.java) -> DetailModel(
                repository as AttractionsRepository
            ) as T

            else -> throw IllegalArgumentException("ViewModel Not Found")
        }

    }


}
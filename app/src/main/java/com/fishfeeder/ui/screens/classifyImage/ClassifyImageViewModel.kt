package com.fishfeeder.ui.screens.classifyImage

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fishfeeder.data.local.util.UiState
import com.fishfeeder.data.remote.model.FishPredictionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import javax.inject.Inject
import com.fishfeeder.data.repository.FishFeederRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel

class ClassifyImageViewModel  @Inject constructor(private val repository : FishFeederRepository) : ViewModel(){

    private val _predictResult: MutableStateFlow<UiState<FishPredictionResponse>> =
        MutableStateFlow(UiState.Loading)
    val predict: StateFlow<UiState<FishPredictionResponse>>
        get() = _predictResult
    fun predictImage(file : File) {
        viewModelScope.launch {
            repository.predict(file).catch {exception ->
                _predictResult.value = UiState.Error(exception.toString())

            }.collect{ data ->
                _predictResult.value = data


            }
        }
    }
    fun onTakePhoto(uri : Uri) {
        Log.d("Bitmap", uri.toString())
    }
}
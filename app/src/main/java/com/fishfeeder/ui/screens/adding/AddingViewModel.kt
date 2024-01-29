package com.fishfeeder.ui.screens.adding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fishfeeder.data.repository.FishFeederRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddingViewModel @Inject constructor(private val fishFeederRepository: FishFeederRepository) : ViewModel() {

    fun onEvent(event : AddingEvent) {
        when (event) {
            is AddingEvent.SaveSchedule -> {
                viewModelScope.launch {
                    fishFeederRepository.insertSchedule(event.timeResult,event.nameSchedule)
                }


            }
        }
    }
}
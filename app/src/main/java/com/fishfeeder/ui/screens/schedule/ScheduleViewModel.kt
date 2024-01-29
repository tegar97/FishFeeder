package com.fishfeeder.ui.screens.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fishfeeder.data.local.entity.ScheduleEntity
import com.fishfeeder.data.local.util.UiState
import com.fishfeeder.data.repository.FishFeederRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val fishFeederRepository: FishFeederRepository) :
    ViewModel() {

    private val _scheduleState: MutableStateFlow<UiState<List<ScheduleEntity>>> =
        MutableStateFlow(UiState.Loading)
    val scheduleState: StateFlow<UiState<List<ScheduleEntity>>>
        get() = _scheduleState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )


    fun fetchAllSchedule() {
        viewModelScope.launch {
            fishFeederRepository.getAllSchedule().catch { exception ->
                _scheduleState.value = UiState.Error(exception.message.orEmpty())

            }.collect { schendule ->
                _scheduleState.value = UiState.Success(schendule)
            }
        }
    }

    fun onEvent(scheduleEvent: ScheduleEvent) {
        when (scheduleEvent) {
            is ScheduleEvent.OnChangeSchedule -> {
                viewModelScope.launch {
                    fishFeederRepository.updateScheduleStatus(
                        scheduleEvent.id,
                        scheduleEvent.isActive
                    )
                }
            }
        }
    }
}
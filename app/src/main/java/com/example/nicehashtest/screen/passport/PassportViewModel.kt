package com.example.nicehashtest.screen.passport

import androidx.lifecycle.viewModelScope
import com.example.nicehashtest.base.BaseViewModel
import com.example.nicehashtest.base.IViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassportViewModel @Inject constructor() :
    BaseViewModel<PassportState, PassportViewEvent>() {

    override fun createInitialState() = PassportState()

    override fun onTriggerEvent(event: PassportViewEvent) {
        viewModelScope.launch {
            when (event) {
                else -> {}
            }
        }
    }
}

sealed class PassportViewEvent : IViewEvent

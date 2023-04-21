package com.mercari.presentation.shared.viewmodel

import kotlinx.coroutines.flow.StateFlow

interface ViewModelContract<INTENT, STATE> {
    val state: StateFlow<STATE>
    fun processIntent(intent: INTENT)
}
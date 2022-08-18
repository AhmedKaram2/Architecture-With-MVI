package com.karam.easymvi.features.home.ui.mainActivity

import androidx.lifecycle.ViewModel
import com.karam.easymvi.features.home.useCases.MainTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val mainTasksUseCase: MainTasksUseCase) :ViewModel(){


}
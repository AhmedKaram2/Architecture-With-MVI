package com.karam.easymvi.features.home.useCases

import com.karam.easymvi.core.apiSettings.BaseUseCase
import com.karam.easymvi.features.home.data.MainScreenDataSource
import javax.inject.Inject

class MainTasksUseCase
    @Inject constructor(private val mainScreenDataSource: MainScreenDataSource)
    : BaseUseCase() {
}
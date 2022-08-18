package com.karam.easymvi.features.home.ui.mainActivity.state

sealed class MainViewState {

    object Idle : MainViewState()
    object Loading : MainViewState()
//    data class UserTasks(val userTasks: LoginResponse?) : MainViewState()
    data class Error(val error: String? = null, val errorMsgId: Int? = null) : MainViewState()
}
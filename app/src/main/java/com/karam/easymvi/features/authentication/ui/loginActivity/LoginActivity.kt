package com.karam.easymvi.features.authentication.ui.loginActivity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import cn.pedant.SweetAlert.SweetAlertDialog
import com.mohre.smartinspectionapp.R
import com.mohre.smartinspectionapp.databinding.ActivityLoginBinding
import com.karam.easymvi.features.authentication.data.model.LoginResponse
import com.karam.easymvi.features.authentication.ui.loginActivity.intent.LoginIntent
import com.karam.easymvi.features.authentication.ui.loginActivity.state.LoginViewState
import com.karam.easymvi.features.home.ui.mainActivity.MainActivity
import com.karam.easymvi.helpers.components.ParentActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : ParentActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        observeViewModel()
        handleLoginButtonClick()
    }

    private fun handleLoginButtonClick() {

        binding.btnLogin.setOnClickListener {
            triggerUserLogin()
        }
    }

    private fun triggerUserLogin() {

        lifecycleScope.launch {

            loginViewModel.loginIntent.send(
                LoginIntent.LoginUserEvent(
                    binding.etUserName.text.toString(),
                    binding.etPassWord.text.toString()
                )
            )
        }
    }

    private fun observeViewModel() {

        lifecycleScope.launch {

            loginViewModel.state.collect {

                when (it) {

                    is LoginViewState.Loading -> {
                        showProgressDialog(getString(R.string.user_logging))
                    }

                    is LoginViewState.User -> {
                        it.user?.let { response ->
                            handleSuccessLogin(response)
                        }
                    }
                    is LoginViewState.Error -> {
                        handleErrorLogin(it.error)
                    }
                    is LoginViewState.Idle -> {
                        hideProgressDialog()
                    }
                }
            }
        }
    }

    private fun handleErrorLogin(error: String?) {
        progressHUD.changeAlertType(SweetAlertDialog.ERROR_TYPE)
        progressHUD.titleText = error ?: getString(R.string.something_went_wrong_please_try_again)
    }

    private fun handleSuccessLogin(loginResponse: LoginResponse) {

        LoginIntent.SaveUserEvent(loginResponse)
        startActivity(Intent(activity, MainActivity::class.java))
    }
}
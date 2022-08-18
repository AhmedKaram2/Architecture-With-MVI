package com.karam.easymvi.features.home.ui.mainActivity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mohre.smartinspectionapp.databinding.ActivityMainBinding
import com.karam.easymvi.helpers.components.ParentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :ParentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }
}
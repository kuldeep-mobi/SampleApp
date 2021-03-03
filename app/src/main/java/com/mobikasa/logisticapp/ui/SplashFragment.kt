package com.mobikasa.logisticapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mobikasa.logisticapp.R
import com.mobikasa.logisticapp.viewmodels.MainViewModel

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.title.postValue("Splash")
    }
}
package com.mobikasa.logisticapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.mobikasa.logisticapp.R
import com.mobikasa.logisticapp.SettingsProto
import com.mobikasa.logisticapp.databinding.FragmentHomeBinding
import com.mobikasa.logisticapp.models.HomeData
import com.mobikasa.logisticapp.ui.adapters.HomeAdapter
import com.mobikasa.logisticapp.utils.LogisticApp
import com.mobikasa.logisticapp.utils.ProtoDataManager
import com.mobikasa.logisticapp.utils.extensions.viewBinding
import com.mobikasa.logisticapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.title.postValue("Home")
        val homeList = listOf(
            HomeData("Gorakhpur", R.drawable.logo),
            HomeData("Khalilabad", R.drawable.logo),
            HomeData("Basti", R.drawable.logo),
            HomeData("Gonda", R.drawable.logo),
            HomeData("Lucknow", R.drawable.logo),
            HomeData("Kanpur", R.drawable.logo),
            HomeData("Aligarh", R.drawable.logo),
            HomeData("Meerut", R.drawable.logo),
            HomeData("Ghaziabad", R.drawable.logo),
            HomeData("New Delhi", R.drawable.logo)
        )
        val protoDataManager = ProtoDataManager.getInstance(LogisticApp.applicationContext())
        Log.d("PRObject", "$protoDataManager")
        lifecycleScope.launch {
            protoDataManager.update(9, "Awadhesh")
            val mProfile =
                SettingsProto.Profile.newBuilder().setFirstName("Kuldeep").setLastName("Prajapati")
                    .setRollNumber(1234).build()
            protoDataManager.updateProfile(mProfile)
        }
        val layootmanager = GridLayoutManager(activity, 6)
        layootmanager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0, 1, 2 -> 2
                    3 -> 6
                    5, 4 -> 2
                    else -> 2
                }
            }
        }
        binding.homeRecycler.layoutManager = layootmanager
        binding.homeRecycler.adapter = HomeAdapter(
            homeList
        )

    }
}
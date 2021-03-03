package com.mobikasa.logisticapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.mobikasa.logisticapp.R
import com.mobikasa.logisticapp.SettingsProto
import com.mobikasa.logisticapp.databinding.ActivityMainBinding
import com.mobikasa.logisticapp.network.APIService
import com.mobikasa.logisticapp.network.ServiceBuilder
import com.mobikasa.logisticapp.repositories.LoginRepository
import com.mobikasa.logisticapp.utils.LogisticApp
import com.mobikasa.logisticapp.utils.ProtoDataManager
import com.mobikasa.logisticapp.utils.SettingManager
import com.mobikasa.logisticapp.utils.ViewModelFactory
import com.mobikasa.logisticapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory(
            LoginRepository(
                (ServiceBuilder.buildService(
                    APIService::class.java
                ))
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(activityMainBinding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
        val toolbar = activityMainBinding.mainToolbar
        appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashFragment) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
            if (destination.id == R.id.loginFragment || destination.id == R.id.homeFragment) {
                toolbar.navigationIcon = null
            }
        }
        Handler().postDelayed({
            navController.navigate(R.id.action_splashFragment_to_loginFragment)
        }, 3000)
        mainViewModel.title.observe(this, {
            Log.i("TAG", "Title Value $it")
        })
        /* activityMainBinding.textName.text = "Welcome to new Project...!!!!"
         activityMainBinding.button.setOnClickListener {
             val intentIntegrator = IntentIntegrator(this)
             intentIntegrator.setPrompt("Scan Bar Code or QR Code")
             intentIntegrator.setOrientationLocked(true)
             intentIntegrator.captureActivity = CapturePotraitActivity::class.java;
             intentIntegrator.initiateScan()
         }*/

        val settingManager = SettingManager.getInstance(LogisticApp.applicationContext())
        lifecycleScope.launch {
            settingManager.updateCounter(12)
            settingManager.data.asLiveData().observe(this@MainActivity) { data ->
                Log.d("DATA", "$data")
            }
        }
        lifecycleScope.launch {
            settingManager.updateName("Kuldeep Kumar")
            settingManager.nameData.asLiveData().observe(this@MainActivity) {
                Log.d("NAME", "$it")
            }
        }

        val protoDataManager = ProtoDataManager.getInstance(LogisticApp.applicationContext())
        lifecycleScope.launch {
            protoDataManager.update(1, "Kuldeep Kumar")
            protoDataManager.dataFlow.asLiveData().observe(this@MainActivity, {
                Log.d("PROTO", "${it.exampleCounter}    ${it.profile}")
            })
        }
        Log.d("PRObject", "$protoDataManager")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val data = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.contents == null) {
                return
            }
            Log.d("RESULT", data.contents)
        }
    }
}
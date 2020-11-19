package com.example.flickr.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.Toolbar
import com.example.flickr.R
import com.example.flickr.core.BaseActivity
import com.example.flickr.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_splash

    override fun getToolBar(binding: ActivitySplashBinding): Toolbar? {
        return null
    }
    override fun onActivityReady(instance: Bundle?, binding: ActivitySplashBinding) {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }, 3000)
    }
}
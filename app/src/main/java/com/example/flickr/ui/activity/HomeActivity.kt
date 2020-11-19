package com.example.flickr.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.example.flickr.R
import com.example.flickr.core.BaseActivity
import com.example.flickr.databinding.ActivityHomeBinding
import com.example.flickr.ui.adapter.TabsAdapter
import com.example.flickr.ui.viewModel.FeedsViewModel
import com.example.flickr.ui.viewModel.ViewModelFactory
import com.example.flickr.utility.Constants
import com.example.flickr.utility.Utility
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class HomeActivity : BaseActivity<ActivityHomeBinding>(), KodeinAware {

    override val layoutResId: Int
        get() = R.layout.activity_home

    override val kodein by closestKodein()
    private lateinit var viewModel: FeedsViewModel
    private val viewModelFactory: ViewModelFactory by instance()

    override fun onActivityReady(instance: Bundle?, binding: ActivityHomeBinding) {

        viewModel = ViewModelProviders.of(this@HomeActivity, viewModelFactory)
            .get(FeedsViewModel::class.java)

        val adapter = TabsAdapter(supportFragmentManager)
        adapter.addFragment(
            com.example.flickr.ui.fragment.ListFragment.getInstance(Constants.TAG_CATS),
            resources.getString(R.string.tab1)
        )
        adapter.addFragment(
            com.example.flickr.ui.fragment.ListFragment.getInstance(Constants.TAG_DOGS),
            resources.getString(R.string.tab2)
        )
        adapter.addFragment(
            com.example.flickr.ui.fragment.ListFragment.getInstance(Constants.TAG_BIRDS),
            resources.getString(R.string.tab3)
        )

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        Utility.showMessageIfNoNetwork(binding.container)
    }

    override fun getToolBar(binding: ActivityHomeBinding): Toolbar? {
        return null
    }

}
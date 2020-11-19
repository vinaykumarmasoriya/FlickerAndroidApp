package com.example.flickr.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickr.R
import com.example.flickr.core.BaseFragment
import com.example.flickr.databinding.FragmentListBinding
import com.example.flickr.ui.adapter.HomeFeedAdapter
import com.example.flickr.ui.viewModel.FeedsViewModel
import com.example.flickr.utility.getViewModel
import kotlinx.coroutines.launch
import java.util.*

const val TAG = "tag"

class ListFragment : BaseFragment<FragmentListBinding>() {

    private lateinit var viewModel: FeedsViewModel
    private lateinit var mBinding: FragmentListBinding
    private val adapter = HomeFeedAdapter()

    override val layoutResId: Int
        get() = R.layout.fragment_list

    override fun onFragmentReady(instanceState: Bundle?, binding: FragmentListBinding) {
        mBinding = binding

        // initializing viewModel from activity scope provide by view model provider
        activity?.let {
            viewModel = it.getViewModel()
        }

        val layoutManager = GridLayoutManager(activity!!, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.isFocusable = false
        binding.recyclerView.adapter = adapter

        arguments?.let {
            val tag = it.getString(TAG)
            tag?.let { tag ->
                viewModel.loadFeedByTag(tag)
                fillData(tag)
            }
        }
    }

    private fun fillData(tag: String) = launch {
        val feeds = viewModel.getFeedByTagAsync(tag).await()
        feeds.observe(this@ListFragment, Observer {
            if (it == null) return@Observer
            Log.d("ListFragment", "Feed Data ****$it")
            mBinding.progress.visibility = View.GONE
            adapter.submitList(it.feeds)
            Handler(Looper.getMainLooper()).post {  mBinding.recyclerView.smoothScrollToPosition(0) }
        })
    }

    companion object {
        fun getInstance(tag: String): ListFragment {
            val bundle = Bundle()
            bundle.putString(TAG, tag)
            val fragment = ListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
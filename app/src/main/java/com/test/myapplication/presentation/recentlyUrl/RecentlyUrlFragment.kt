package com.test.myapplication.presentation.recentlyUrl

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.myapplication.databinding.FragmentRecentUrlBinding
import com.test.myapplication.domain.model.ShortenUrlItem
import com.test.myapplication.presentation.handler.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentlyUrlFragment : Fragment() {

    private var _binding: FragmentRecentUrlBinding? = null

    private val binding get() = _binding!!

    private lateinit var urlAdapter: RecentlyUrlAdapter

    private val urlList = mutableListOf<ShortenUrlItem>()

    private val recentlyUrlViewModel: RecentlyUrlViewModel by viewModels()

    private val sharedViewModel by lazy {
        activity?.let {
            ViewModelProvider(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentRecentUrlBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        initData()
    }

    private fun initRecyclerView() {
        urlAdapter = RecentlyUrlAdapter(urlList)
        with(binding.containerRecentUrl) {
            layoutManager = LinearLayoutManager(activity)
            adapter = urlAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                recentlyUrlViewModel.uiState.collect() { info ->
                    handleData(info.data)
                    handleLoader(info.inProgress)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                sharedViewModel?.uiState?.collect() { data ->
                    if (data.newUrlAdd) {
                        recentlyUrlViewModel.onCreate()
                    }
                }
            }
        }
    }

    private fun initData() {
        recentlyUrlViewModel.onCreate()
    }

    private fun handleData(data: List<ShortenUrlItem>) {
        if (data.isNotEmpty()) {
            urlList.clear()
            urlList.addAll(data)
            urlAdapter.notifyDataSetChanged()
        } else {
            //Show a label explaining there's no info yet
        }
    }

    private fun handleLoader(inProgress: Boolean) {

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
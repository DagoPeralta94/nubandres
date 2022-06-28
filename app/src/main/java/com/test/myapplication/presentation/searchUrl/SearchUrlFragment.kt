package com.test.myapplication.presentation.searchUrl

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.myapplication.databinding.FragmentUrlSearchBinding
import com.test.myapplication.presentation.handler.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchUrlFragment : Fragment() {

    private var _binding: FragmentUrlSearchBinding? = null

    private val binding get() = _binding!!

    private val searchUrlViewModel: SearchUrlViewModel by viewModels()

    private val sharedViewModel by lazy {
        activity?.let {
            ViewModelProvider(it)[MainViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentUrlSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.buttonUrlSearch.setOnClickListener {
            searchUrlViewModel.onUrlToSearch(binding.editTextScreenUrl.text.toString())
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                searchUrlViewModel.uiState.collect { uiState ->
                    with(binding) {
                        val error = handlerError(uiState.dotError, uiState.wwwError)
                        wrapperUrl.error = error?.let(this@SearchUrlFragment::getString)
                        progressUrl.visibility = if (uiState.inProgress) View.VISIBLE else View.GONE
                        if (uiState.isUrlCorrect) {
                            searchUrlViewModel.onUrlCorrect(binding.editTextScreenUrl.text.toString())
                        }
                        if (uiState.addedNewUrl) {

                            cleanText()
                            sharedViewModel?.onUrlAdded(uiState.result)
                            closeKeyboard()
                        }
                    }
                }
            }
        }
    }

    private fun cleanText() {
        binding.editTextScreenUrl.setText("")
    }

    private fun closeKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun handlerError(dotError: Int?, wwwError: Int?): Int? = wwwError ?: dotError

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
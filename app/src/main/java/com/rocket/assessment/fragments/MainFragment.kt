package com.rocket.assessment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rocket.assessment.R
import com.rocket.assessment.adapters.GameListAdapter
import com.rocket.assessment.databinding.FragmentMainBinding
import com.rocket.assessment.entities.LoadResult
import com.rocket.assessment.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [Fragment] for the main page (Game List)
 */
class MainFragment: Fragment() {

    private var dataFetched: Boolean = false
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = GameListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.btnSearch.setOnClickListener {
            searchGameResult()
        }
        binding.btnRetry.setOnClickListener {
            searchGameResult()
        }

        adapter.setItemClickListener {
            val bundle = Bundle().apply {
                putParcelable(DetailFragment.ARG_GAME_ITEM, it)
            }
            findNavController().navigate(R.id.action_detailFragment, bundle)
        }

        viewModel.gameList.observe(viewLifecycleOwner) {
            dataFetched = true
            adapter.updateKeyword(viewModel.keyword)
            adapter.submitList(it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it == LoadResult.LOADING
            binding.recyclerView.isVisible = it == LoadResult.SUCCESS
            binding.errorViews.isVisible = it == LoadResult.FAILURE
        }

        if (savedInstanceState == null && !dataFetched) {
            viewModel.fetchGameResult("")
        }
    }

    private fun searchGameResult() {
        val keyword = binding.searchEditText.text.toString()
        viewModel.fetchGameResult(keyword)
    }
}
package com.canwar.baseproject.ui.fragments.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.canwar.baseproject.databinding.FragmentHomeBinding
import com.canwar.baseproject.ui.adapters.GameAdapter
import com.canwar.baseproject.ui.adapters.LoadingStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    @Inject lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        action()
        subscribeObserver()

        return binding.root
    }

    private fun action() {
        binding.etSearch.doAfterTextChanged {
            viewModel.getGameData(it.toString())
        }

        gameAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                gameAdapter.retry()
            }
        )

        binding.rvGame.apply {
            layoutManager = LinearLayoutManager(requireContext())

            adapter = gameAdapter
        }
    }

    private fun subscribeObserver() {
        lifecycleScope.launch {
            viewModel.pagingDataGame.collectLatest {
                gameAdapter.submitData(it)
            }

            viewModel.throwable.collectLatest {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
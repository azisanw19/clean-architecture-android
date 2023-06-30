package com.canwar.baseproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.canwar.baseproject.databinding.FragmentFavoriteBinding
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.rvFavorite.layoutManager = LinearLayoutManager(this.context)

        return binding.root
    }

}
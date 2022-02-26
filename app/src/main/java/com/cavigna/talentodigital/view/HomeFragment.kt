package com.cavigna.talentodigital.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cavigna.talentodigital.databinding.FragmentHomeBinding
import com.cavigna.talentodigital.utils.loadingHome
import com.cavigna.talentodigital.view.adapter.HomeAdapter
import com.cavigna.talentodigital.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.ExtraerMoneda {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recycler: RecyclerView
    private val homeAdapter by lazy { HomeAdapter(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        recycler = binding.recyclerView

        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }

        viewModel.listOfCoins.observe(viewLifecycleOwner, {
            when(it.isEmpty()){
                true -> loadingHome(true, binding)
                false -> {
                    loadingHome(false, binding)
                    homeAdapter.submitList(it)
                }
            }
        })

        return binding.root
    }

    override fun extraerId(id: String) {
        viewModel.selectOrFetchCoinDetails(id)
    }


}
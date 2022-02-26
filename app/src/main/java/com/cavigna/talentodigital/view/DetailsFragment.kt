package com.cavigna.talentodigital.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.cavigna.talentodigital.R
import com.cavigna.talentodigital.databinding.FragmentDetailsBinding
import com.cavigna.talentodigital.utils.*
import com.cavigna.talentodigital.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        viewModel.coinDetails.observe(viewLifecycleOwner, { coinDetails ->
            when{
                coinDetails.id.isEmpty() -> loadingDetails(true, binding)
                coinDetails.id.isNotEmpty() ->{
                    loadingDetails(false, binding)
                    binding.apply {
                        appBarImage.loadSvg(coinDetails.logoUrl)
                        textViewTitleDetails.text = coinDetails.name
                        textViewRank.text = getString(R.string.rank, coinDetails.rank)
                        textViewSymbolDetails.text = coinDetails.symbol
                        textViewPrice.text = getString(R.string.price, redondeo(coinDetails.price))
                        textViewPriceDate.text = getString(R.string.price_date, parseDate(coinDetails.priceDate))




                        fb.setOnClickListener {
                            sendEmailIntent(coinDetails, requireContext())
                        }

                    }
                }
            }
        })

        return binding.root
    }


}
package com.rainbowt.traveltaipei.ui.main.attraction

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rainbowt.traveltaipei.R
import com.rainbowt.traveltaipei.data.api.TravelTaipeiApi
import com.rainbowt.traveltaipei.data.repository.AttractionsRepository
import com.rainbowt.traveltaipei.Constants
import com.rainbowt.traveltaipei.data.model.Data
import com.rainbowt.traveltaipei.data.model.Detail
import com.rainbowt.traveltaipei.databinding.FragmentAttractionBinding
import com.rainbowt.traveltaipei.ui.base.BaseFragment
import com.rainbowt.traveltaipei.ui.main.detail.DetailFragment.Companion.TAG
import com.rainbowt.traveltaipei.utils.showLangSelectionDialog

class AttractionFragment : BaseFragment<FragmentAttractionBinding, AttractionModel>() {

    override fun initToolBar() {
        setTitleText(
            resources.getString(R.string.app_name),
            ContextCompat.getColor(requireContext(), R.color.white)
        )
        hideLeftContentIcon()
        showRightContentIcon(R.drawable.ic_language_24, onClick = {
            requireContext().showLangSelectionDialog(onClick = { lang->
                changeLang(lang)
            })
        })
    }

    override fun initViews() {
        initRvAttraction()
    }

    override fun getLayoutId() = R.layout.fragment_attraction

    override fun getViewModel() = AttractionModel::class.java

    override fun getRepository() = AttractionsRepository(
        remoteDataSource.buildApi(TravelTaipeiApi::class.java)
    )

    private fun changeLang(languageCode: String) {
        Constants.languageCode = languageCode
        viewModel.getAttractions()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.attraction.observe(viewLifecycleOwner, Observer {
            (binding.rvAttraction.adapter as AttractionAdapter).setData(it.data.toMutableList())
        })
        viewModel.getAttractions()
    }

    private fun initRvAttraction() {
        binding.rvAttraction.adapter = AttractionAdapter(context,
            onItemClick = {
                navToDetailFragment(it)
            }
        )
        binding.rvAttraction.layoutManager = LinearLayoutManager(context)
        binding.rvAttraction.hasFixedSize()
        binding.rvAttraction.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun navToDetailFragment(attraction: Data) {
        val detail = Detail(
            if (attraction.images.isNotEmpty()) (attraction.images[0].src!!) else null,
            attraction.name!!,
            attraction.introduction!!,
            attraction.address!!,
            attraction.modified!!,
            attraction.official_site!!
        )
        val bundle = Bundle().apply { putParcelable(TAG, detail) }
        findNavController().navigate(R.id.attraction_to_detail, bundle)
    }

}
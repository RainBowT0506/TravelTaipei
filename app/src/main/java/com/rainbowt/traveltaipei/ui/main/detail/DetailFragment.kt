package com.rainbowt.traveltaipei.ui.main.detail

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.rainbowt.traveltaipei.*
import com.rainbowt.traveltaipei.data.api.TravelTaipeiApi
import com.rainbowt.traveltaipei.data.model.Detail
import com.rainbowt.traveltaipei.data.repository.AttractionsRepository
import com.rainbowt.traveltaipei.databinding.FragmentDetailBinding
import com.rainbowt.traveltaipei.ui.base.BaseFragment
import com.rainbowt.traveltaipei.ui.main.web.WebFragment
import com.rainbowt.traveltaipei.utils.loadImage

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailModel>() {

    override fun getLayoutId() = R.layout.fragment_detail

    override fun getViewModel() = DetailModel::class.java

    override fun getRepository() = AttractionsRepository(
        remoteDataSource.buildApi(TravelTaipeiApi::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        arguments?.let {
            viewModel.detail.value = it.getParcelable(TAG)
        }

    }

    override fun initToolBar() {
        viewModel.detail.value?.name?.let {
            setTitleText(
                it,
                ContextCompat.getColor(requireContext(), R.color.white)
            )
        }
        showLeftContentIcon(R.drawable.ic_arrow_back_left_24,
            onClick = {
                activity?.onBackPressed()
            }
        )
        hideRightContentIcon()
    }

    override fun initViews() {
        val detail = viewModel.detail.value
        initDetailPage(detail)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val detail = viewModel.detail.value

//        initToolbar(detail)

//        initDetailPage(detail)
    }

    private fun initToolbar(detail: Detail?) {
        activity?.title = detail?.name
        (context as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initDetailPage(detail: Detail?) {
        loadImage(binding.image, detail?.imageUrl)
        binding.title.text = detail?.name
        binding.content.text = detail?.introduction
        binding.address.text = detail?.address
        binding.updatedTime.text = detail?.lastUpdatedTime

        binding.officialSite.text =
            Html.fromHtml("<a href='${detail?.officialSite}'>${detail?.officialSite}</a>")
        binding.officialSite.setOnClickListener {
            jumpToWebFragment(detail)
        }
    }

    private fun jumpToWebFragment(detail: Detail?) {
        val bundle = Bundle().apply {
            putString(WebFragment.URL, detail?.officialSite)
        }
        findNavController().navigate(R.id.detail_to_web, bundle)
        //  cause WebFragment not extend BaseFragment
        hideRightContentIcon()
    }


    companion object {
        val TAG = DetailFragment::class.java.toString()
    }
}
package com.rainbowt.traveltaipei.ui.main.detail

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
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
            viewModel.detail.value = Detail(
                imageUrl = it.getString(IMAGE_URL),
                name = it.getString(NAME),
                introduction = it.getString(INTRODUCTION),
                address = it.getString(ADDRESS),
                lastUpdatedTime = it.getString(LAST_UPDATED_TIME),
                officialSite = it.getString(OFFICIAL_SITE),
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var detail = viewModel.detail.value

        activity?.title = detail?.name

        initDetailPage(detail)
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
        (context as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, WebFragment.newInstance(detail?.officialSite))
            .addToBackStack(detail?.officialSite)
            .commit()
    }


    companion object {
        private const val IMAGE_URL = "image_url"
        private const val NAME = "name"
        private const val INTRODUCTION = "introduction"
        private const val ADDRESS = "address"
        private const val LAST_UPDATED_TIME = "last_updated_time"
        private const val OFFICIAL_SITE = "official_site"

        @JvmStatic
        fun newInstance(detail: Detail
        ) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_URL, detail.imageUrl)
                    putString(NAME, detail.name)
                    putString(INTRODUCTION, detail.introduction)
                    putString(ADDRESS, detail.address)
                    putString(LAST_UPDATED_TIME, detail.lastUpdatedTime)
                    putString(OFFICIAL_SITE, detail.officialSite)
                }
            }
    }
}
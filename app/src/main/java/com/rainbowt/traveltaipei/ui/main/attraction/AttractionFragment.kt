package com.rainbowt.traveltaipei.ui.main.attraction

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
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

class AttractionFragment : BaseFragment<FragmentAttractionBinding, AttractionModel>() {


    override fun getLayoutId() = R.layout.fragment_attraction

    override fun getViewModel() = AttractionModel::class.java

    override fun getRepository() = AttractionsRepository(
        remoteDataSource.buildApi(TravelTaipeiApi::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tw -> changeLang("zh-tw")
            R.id.cn -> changeLang("zh-cn")
            R.id.en -> changeLang("en")
            R.id.ja -> changeLang("ja")
            R.id.ko -> changeLang("ko")
            R.id.es -> changeLang("es")
            R.id.id -> changeLang("id")
            R.id.th -> changeLang("th")
            R.id.vi -> changeLang("vi")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeLang(languageCode: String) {
        Constants.languageCode = languageCode
        viewModel.getAttractions()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = resources.getString(R.string.app_name)

        initRvAttraction()
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
package com.ian.junemon.spe_learning_mvvm.tv.ui.slider

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.ian.app.helper.util.inflates
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.movie.ui.MovieFragmentDirections
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvAiringTodayData
import kotlinx.android.synthetic.main.item_slider.view.*

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class SliderTvAdapter(private val data: List<TvAiringTodayData>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val views = container.inflates(R.layout.item_slider)
        views.ivSliderImage.loadWithGlide(data[position].poster_path)
        views.ivSliderImage?.setOnClickListener {
            it.findNavController().navigate(MovieFragmentDirections.actionHomeFragmentToDetailMovieFragment(data[position].id!!))

        }
        container.addView(views)
        return views
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount() = data.size
}
package com.ian.app.muviepedia.movie.movie.ui.slider

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.ian.app.helper.util.inflates
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.MovieNowPlayingLocalData
import com.ian.app.muviepedia.movie.R
import com.ian.app.muviepedia.movie.movie.ui.MovieFragmentDirections
import kotlinx.android.synthetic.main.item_slider.view.*

/**
 *
Created by Ian Damping on 12/09/2019.
Github = https://github.com/iandamping
 */
class SliderMovieAdapter(private val data: List<MovieNowPlayingLocalData>) : PagerAdapter() {

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
package com.ian.junemon.spe_learning_mvvm.movie.ui.slider

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.ian.app.helper.util.inflates
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.MovieNowPlayingLocalData
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

            /*ctx?.startActivity<DetailActivity> {
                putExtra(intentToDetail, this@SliderMovieItemAdapter.data[position].id)
            }*/
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
package com.ian.junemon.spe_learning_mvvm.ui.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.ian.app.helper.util.inflates
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.BuildConfig.imageFormatter
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.databinding.ItemSliderBinding
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import kotlinx.android.synthetic.main.item_slider.view.*

/**
 *
Created by Ian Damping on 12/09/2019.
Github = https://github.com/iandamping
 */
class SliderMovieAdapter(private val data: List<MovieData>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val views = container.inflates(R.layout.item_slider)
        views.ivSliderImage.loadWithGlide(imageFormatter + data[position].poster_path)
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
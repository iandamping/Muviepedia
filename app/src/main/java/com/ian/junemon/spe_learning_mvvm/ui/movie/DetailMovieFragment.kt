package com.ian.junemon.spe_learning_mvvm.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.BuildConfig.imageFormatter
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.MovieViewmodel
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentDetailBinding
import com.ian.recyclerviewhelper.helper.setUpVertical
import kotlinx.android.synthetic.main.item_similar.*
import kotlinx.android.synthetic.main.item_similar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {
    private val vm: MovieViewmodel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.movieViewmodel = vm
        val args = DetailMovieFragmentArgs.fromBundle(arguments!!)
        initData(binding,args.movieID)
        return binding.root
    }

    private fun initData(binding:FragmentDetailBinding,movieID:Int){
        vm.detailMovie(movieID).also {
            vm.concurentDetailData.observe(this@DetailMovieFragment.viewLifecycleOwner, Observer { movieData->
                movieData.first.poster_path = imageFormatter+ movieData.first.poster_path
                binding.detailData = movieData.first
                binding.rvSimilarMovie.setUpVertical(movieData.second,R.layout.item_similar,{
                    ivSimilarMovie.loadWithGlide(imageFormatter + it.poster_path)
                    tvSimilarMovieTittle.text = it.title
                    tvSimilarMovieReleaseDate.text = it.release_date
                })
            })
        }

    }


}

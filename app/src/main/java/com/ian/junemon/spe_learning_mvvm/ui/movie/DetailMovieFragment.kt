package com.ian.junemon.spe_learning_mvvm.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.BuildConfig.imageFormatter
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.MovieViewmodel
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentDetailBinding
import com.ian.junemon.spe_learning_mvvm.util.movieAdapterCallback
import com.ian.recyclerviewhelper.helper.setUpVerticalListAdapter
import kotlinx.android.synthetic.main.item_similar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {
    private val vm: MovieViewmodel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args = DetailMovieFragmentArgs.fromBundle(arguments!!)
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        vm.detailMovie(args.movieID)
        binding.movieViewmodel = vm
        initData(binding)
        return binding.root
    }

    private fun initData(binding: FragmentDetailBinding) {
        binding.apply {
            vm.concurentDetailData.observe(this@DetailMovieFragment.viewLifecycleOwner, Observer { movieData ->
                movieData.first.poster_path = imageFormatter + movieData.first.poster_path
                detailData = movieData.first
                toolbars.title = movieData.first.title
                rvSimilarMovie.setUpVerticalListAdapter(movieData.second, movieAdapterCallback, R.layout.item_similar, {
                    ivSimilarMovie.loadWithGlide(imageFormatter + it.poster_path)
                    tvSimilarMovieTittle.text = it.title
                    tvSimilarMovieReleaseDate.text = it.release_date
                }, {
                    if (id != null) vm.detailMovie(id!!)
                })
                invalidateAll()
            })
            ivBack.setOnClickListener {
                //much better back pressed
                NavHostFragment.findNavController(this@DetailMovieFragment).navigateUp()
            }
        }


    }

    /* //handle backpressed
     override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)
         requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
             override fun handleOnBackPressed() {
                 NavHostFragment.findNavController(this@DetailMovieFragment).navigateUp()
             }
         })
     }*/


}

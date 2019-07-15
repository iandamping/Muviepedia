package com.ian.junemon.spe_learning_mvvm.model

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class GenericMovieModel<T>(val page: Int?, val total_results: Int?, var total_pages: Int?, val results: List<T>)
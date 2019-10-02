package com.ian.app.muviepedia.data.api

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class ResultResponse<T>(val page: Int?, val total_results: Int?, var total_pages: Int?, val results: List<T>)
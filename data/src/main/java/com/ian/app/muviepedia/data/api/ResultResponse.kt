package com.ian.app.muviepedia.data.api

import com.google.gson.annotations.SerializedName

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class ResultResponse<T>(
    @field:SerializedName("page")val page: Int?,
    @field:SerializedName("total_results")val total_results: Int?,
    @field:SerializedName("total_pages")var total_pages: Int?,
    @field:SerializedName("results")val results: List<T>
)
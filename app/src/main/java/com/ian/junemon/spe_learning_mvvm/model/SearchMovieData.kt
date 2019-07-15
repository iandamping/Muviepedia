package com.ian.junemon.spe_learning_mvvm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
Created by Ian Damping on 26/06/2019.
Github = https://github.com/iandamping
 */
@Parcelize
data class SearchMovieData(var id: Int?,
                           var type: String?,
                           var title: String?,
                           var releaseDate: String?,
                           var overview: String?,
                           var backdropPath: String?) : Parcelable
package com.ian.junemon.spe_learning_mvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalDao
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalData

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Database(entities = [MovieLocalData::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieLocalDao
}
package com.ian.app.muviepedia.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalNowPlayingEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSaveDetailEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSearchEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingPaginationEntity
import com.ian.app.muviepedia.data.db.dao.profile.UserProfileDao
import com.ian.app.muviepedia.data.cleanarch.datasource.model.profile.UserProfileEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSaveDetailEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSearchEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedPaginationEntity
import com.ian.app.muviepedia.data.db.dao.movie.MovieNowPlayingDao
import com.ian.app.muviepedia.data.db.dao.movie.MoviePopularDao
import com.ian.app.muviepedia.data.db.dao.movie.MoviePopularPaginationDao
import com.ian.app.muviepedia.data.db.dao.movie.MovieSaveDetailDao
import com.ian.app.muviepedia.data.db.dao.movie.MovieSearchDao
import com.ian.app.muviepedia.data.db.dao.movie.MovieUpComingDao
import com.ian.app.muviepedia.data.db.dao.movie.MovieUpComingPaginationDao
import com.ian.app.muviepedia.data.db.dao.tv.TvPopularDao
import com.ian.app.muviepedia.data.db.dao.tv.TvPopularPaginationDao
import com.ian.app.muviepedia.data.db.dao.tv.TvAiringTodayDao
import com.ian.app.muviepedia.data.db.dao.tv.TvSaveDetailDao
import com.ian.app.muviepedia.data.db.dao.tv.TvSearchDao
import com.ian.app.muviepedia.data.db.dao.tv.TvTopRatedDao
import com.ian.app.muviepedia.data.db.dao.tv.TvTopRatedPaginationDao
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalAiringTodayEntity

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Database(entities = [MovieLocalNowPlayingEntity::class,
    MovieLocalPopularEntity::class,
    MovieLocalPopularPaginationEntity::class,
    MovieLocalUpComingEntity::class,
    MovieLocalUpComingPaginationEntity::class,
    MovieLocalSearchEntity::class,
    MovieLocalSaveDetailEntity::class,
    TvLocalAiringTodayEntity::class,
    TvLocalPopularEntity::class,
    TvLocalPopularPaginationEntity::class,
    TvLocalTopRatedEntity::class,
    TvLocalTopRatedPaginationEntity::class,
    TvLocalSearchEntity::class,
    TvLocalSaveDetailEntity::class,
    UserProfileEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieNowPlayingDao(): MovieNowPlayingDao
    abstract fun moviePopularDao(): MoviePopularDao
    abstract fun movieUpComingDao(): MovieUpComingDao
    abstract fun movieUpComingPaginationDao(): MovieUpComingPaginationDao
    abstract fun moviePopularPaginationDao(): MoviePopularPaginationDao
    abstract fun movieSearchDao(): MovieSearchDao
    abstract fun movieSaveDetailDao(): MovieSaveDetailDao

    abstract fun tvAiringTodayDao(): TvAiringTodayDao
    abstract fun tvPopularDao(): TvPopularDao
    abstract fun tvTopRatedDao(): TvTopRatedDao
    abstract fun tvTopRatedPaginationDao(): TvTopRatedPaginationDao
    abstract fun tvPopularPaginationDao(): TvPopularPaginationDao
    abstract fun tvSearchDao(): TvSearchDao
    abstract fun tvSaveDetailDao(): TvSaveDetailDao

    abstract fun userProfileDao(): UserProfileDao
}

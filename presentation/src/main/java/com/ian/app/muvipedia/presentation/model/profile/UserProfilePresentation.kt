package com.ian.app.muvipedia.presentation.model.profile

import com.ian.app.muviepedia.model.profile.UserProfileData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class UserProfilePresentation(
    var localID: Int?,
    var userID: String?,
    var photoUser: String?,
    var nameUser: String?,
    var emailUser: String?
)

fun UserProfileData.mapToPresentation(): UserProfilePresentation = UserProfilePresentation(localID, userID, photoUser, nameUser, emailUser)
fun UserProfilePresentation.mapToDomain(): UserProfileData = UserProfileData(localID, userID, photoUser, nameUser, emailUser)

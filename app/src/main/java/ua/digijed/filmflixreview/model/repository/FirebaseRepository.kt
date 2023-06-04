package ua.digijed.filmflixreview.model.repository

import ua.digijed.filmflixreview.data.User

interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}
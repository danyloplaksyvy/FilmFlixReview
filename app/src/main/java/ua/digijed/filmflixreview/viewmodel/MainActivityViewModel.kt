package ua.digijed.filmflixreview.viewmodel


import ua.digijed.filmflixreview.data.User
import ua.digijed.filmflixreview.model.repository.FirebaseRepository
import ua.digijed.filmflixreview.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {
    private val mFirebaseRepository : FirebaseRepository = FirebaseRepositoryImpl()
    fun updateUserData(firebaseUser: User, uid: String){
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}
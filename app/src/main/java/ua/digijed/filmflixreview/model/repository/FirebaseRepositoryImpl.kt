package ua.digijed.filmflixreview.model.repository

import ua.digijed.filmflixreview.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl : FirebaseRepository {

    private var database : DatabaseReference = Firebase.database.reference  // об'єкт для запису в БД

    override fun updateUserData(firebaseUser: User, uid: String) {
        database.child("users").child(uid).setValue(firebaseUser) // збереження користувача в Firebase
    }
}
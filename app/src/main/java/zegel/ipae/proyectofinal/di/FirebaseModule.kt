package zegel.ipae.proyectofinal.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Provides
import javax.inject.Singleton

object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseDatabaseIntance(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStoreIntance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthIntance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}
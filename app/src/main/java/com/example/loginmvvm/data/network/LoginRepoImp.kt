package com.example.loginmvvm.data.network

import com.example.loginmvvm.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoginRepoImp : LoginRepo{

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val mFirestore : FirebaseFirestore = FirebaseFirestore.getInstance()


    // login con email y contraseña

    override suspend fun loginRepo(email: String, password: String): String?{
        return try {
            mAuth.signInWithEmailAndPassword(email, password).await()
            null
        } catch (e:Exception){
            e.localizedMessage
        }
    }


    // registrar un un usuario en firestore

    override suspend fun createUserRepo(email: String, password: String, name:String, lastName:String, phone:String): String?{

        return try {
            mAuth.createUserWithEmailAndPassword(email,password).await()

            val id:String = mAuth.currentUser!!.uid

            val user = User(id,name,lastName,phone, email)
            mFirestore.collection("Users").document(id).set(user).await()
            null

        }catch (e:Exception){
            e.localizedMessage
        }
    }




    // iniciar sesion con google

    override suspend fun firebaseAuthWithGoogleRepo(idToken: String): String? {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            mAuth.signInWithCredential(credential).await()
            null

        }catch (e:Exception){
            e.localizedMessage
        }
    }




    // devuelve si el usuario existe en firestore o no

    override suspend fun userExistRepo(): String? {
        return try {
            var result:String = ""
            val id:String = mAuth.currentUser!!.uid
            mFirestore.collection("Users").document(id).get().addOnSuccessListener { doc ->
                if (doc.exists()){
                    result = "exist"
                }
                else{
                    result = "none"
                }
            }.await()

            result

        }catch (e:Exception){
            e.localizedMessage
        }
    }




    // completa el usuario iniciado con google

    override suspend fun completeUserDataRepo(name: String, lastName: String, phone: String): String? {
        return try {
            val email:String = mAuth.currentUser!!.email!!
            val id:String = mAuth.currentUser!!.uid

            val user = User(id,name,lastName,phone, email)
            mFirestore.collection("Users").document(id).set(user).await()
            null
        }catch (e:Exception){
            e.localizedMessage

        }
    }


    override suspend fun logoutRepo() {
        mAuth.signOut()
    }

    override suspend fun updateUserDataRepo(name: String, lastName: String, phone: String ): String? {
        return try {
            val id:String = mAuth.currentUser!!.uid
            //val email:String = mAuth.currentUser!!.email.toString()
            val map: HashMap<String,Any> = hashMapOf()

            //val user = User(id= id, email = email)

            if (name.isNotBlank()){
                map["name"] = name
            }
            if (lastName.isNotBlank()){
                map["lastname"] = lastName
            }
            if (phone.isNotBlank()){
                map["phone"] = phone
            }



            mFirestore.collection("Users").document(id).update(map).await()
            null
        }catch (e:Exception){
            e.localizedMessage
        }
    }


    override suspend fun getUserDataRepo() : User {
        val id:String = mAuth.currentUser!!.uid
        val user = User()

        mFirestore.collection("Users").document(id).get().addOnSuccessListener {
            user.name = it.getString("name").toString()
            user.lastname = it.getString("lastname").toString()
            user.phone = it.getString("phone").toString()
            user.email = it.getString("email").toString()
        }.await()

        return user

    }

}
package com.example.authetication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_authentication.*
import kotlinx.android.synthetic.main.activity_authentication.signUpButton
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

   private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
    }
    private fun init(){
        auth = Firebase.auth
        signUpButton.setOnClickListener {
            sighUp()

        }

    }
    private fun sighUp(){
        val email =emailEditText.text.toString()
        val password=passwordEditText.text.toString()
        val repeatPassword=repeatEditText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty()){
            if (password==repeatPassword){
               if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                progressBar.visibility= View.VISIBLE
                signUpButton.isClickable=false
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        progressBar.visibility=View.GONE
                        signUpButton.isClickable=true
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            d("sighUp", "createUserWithEmail:success")
                            val user = auth.currentUser

                        } else {
                            // If sign in fails, display a message to the user.
                            d("signUp", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                        }

                        // ...
                    }

                }else{
                   Toast.makeText(this, "email format is not right",  Toast.LENGTH_SHORT).show()
               
               } 
            }else{
                Toast.makeText(this, "Please fill correctly",  Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Please fill all of them",  Toast.LENGTH_SHORT).show()
        }

    }
}

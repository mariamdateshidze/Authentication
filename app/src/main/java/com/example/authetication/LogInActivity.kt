package com.example.authetication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.w
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_authentication.*
import kotlinx.android.synthetic.main.activity_authentication.logInButton
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.emailEditText
import android.util.Log.d

class LogInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        init()
    }
    private fun init(){
        auth = Firebase.auth
        logInButton2.setOnClickListener {
            logIn()
        }
    }
    private fun logIn(){
       val emaillog=emaillogEditText.text.toString()
       val passwordlog=passwordlogEditText.text.toString()
        if (emaillog.isNotEmpty() && passwordlog.isNotEmpty()){
            if (emailEditText==emaillogEditText && passwordEditText==passwordlogEditText){
                progressBar2.visibility= View.VISIBLE
                logInButton2.isClickable=false
                auth.signInWithEmailAndPassword(emaillog, passwordlog)
                    .addOnCompleteListener(this) { task ->
                        progressBar2.visibility=View.GONE
                        logInButton2.isClickable=true
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            d("logIn", "signInWithEmail:success")
                            val user = auth.currentUser

                        } else {
                            // If sign in fails, display a message to the user.
                            d("logIn", "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                            // ...
                        }

                        // ...
                    }




            }else{
                Toast.makeText(this, "Please fill correctly",  Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Please fill all fields",  Toast.LENGTH_SHORT).show()
        }

    }
}




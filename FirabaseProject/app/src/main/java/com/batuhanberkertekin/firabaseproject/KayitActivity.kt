package com.batuhanberkertekin.firabaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.batuhanberkertekin.firabaseproject.databinding.ActivityKayitBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class KayitActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityKayitBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKayitBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

    auth = Firebase.auth









    }

    fun kayitButton(view :View){


        auth.createUserWithEmailAndPassword(binding.epostaText1.text.toString(),binding.passwordText1.text.toString()).addOnSuccessListener {

            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
             Toast.makeText(applicationContext,"BASARILI KAYIT",Toast.LENGTH_LONG).show()

        }.addOnFailureListener {
            Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()

        }







    }








}
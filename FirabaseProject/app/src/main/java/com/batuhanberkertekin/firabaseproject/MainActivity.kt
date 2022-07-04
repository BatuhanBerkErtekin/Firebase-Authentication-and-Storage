package com.batuhanberkertekin.firabaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.batuhanberkertekin.firabaseproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val intent = Intent(applicationContext,KayitActivity::class.java)
        startActivity(intent)



        return super.onOptionsItemSelected(item)
    }



   fun girisButton(view : View){

       val eposta = binding.epostaText.text.toString()
       val password = binding.passwordText.text.toString()

       if(eposta.isNotEmpty() !=null && password.isNotEmpty() != null){
           auth.signInWithEmailAndPassword(eposta,password).addOnCompleteListener {task ->


              if(task.isSuccessful){
                  val intent = Intent(applicationContext,UploadActivity::class.java)
                  startActivity(intent)
                  finish()

                  Toast.makeText(applicationContext,"HOSGELDINIZ",Toast.LENGTH_LONG).show()
              }

       }.addOnFailureListener {

           Toast.makeText(applicationContext,"BOS BIRAKMAYINIZ LUTFEN",Toast.LENGTH_LONG).show()
           }




       }
       }


   }



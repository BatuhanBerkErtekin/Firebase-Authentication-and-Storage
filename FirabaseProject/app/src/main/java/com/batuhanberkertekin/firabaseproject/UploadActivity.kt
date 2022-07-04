package com.batuhanberkertekin.firabaseproject

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.batuhanberkertekin.firabaseproject.databinding.ActivityUploadBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.google.geo.type.ViewportProto
import com.squareup.picasso.Picasso
import java.util.*

class UploadActivity : AppCompatActivity() {

  private lateinit var  storage : FirebaseStorage
  private  lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
  private  lateinit var  permissinLauncher: ActivityResultLauncher<String>
    private lateinit var firebaseStorage : FirebaseFirestore
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityUploadBinding
    private var storageReference : StorageReference? = null
     var picture  : Uri? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityUploadBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        storage =Firebase.storage
        auth = Firebase.auth
        firebaseStorage = Firebase.firestore
        storageReference = FirebaseStorage.getInstance().reference

      registerLauncher()



    }


    fun upload(view :View){

        val uuid = UUID.randomUUID()
        val imageName = "$uuid.jpg"
           val reference =storage.reference
         val imageReference = reference.child("picture").child(imageName)
            if(picture !=null){


                imageReference.putFile(picture!!).addOnSuccessListener {
                    Toast.makeText(this,"FOTOGRAF KAYDEDILDI",Toast.LENGTH_LONG).show()

                }.addOnFailureListener{
                    Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                }

            }

    }

  fun selectImage(view :View){

      if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
          if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
              Snackbar.make(view,"IZIN VERINIZ LUTFEN",Snackbar.LENGTH_INDEFINITE).setAction("IZIN VERINIZ"){
              permissinLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
              }.show()
          }else{
              permissinLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
          }
      }else{
          val intentGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentGallery)
      }
  }
     private  fun registerLauncher(){

         activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
             if(it.resultCode == RESULT_OK){
                 val intentFromResult = it.data
                 if(intentFromResult!=null){
                  picture =   intentFromResult.data
                     picture?.let {
                         binding.imageView2.setImageURI(it)
                     }
                 }
             }
         }
         permissinLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
             if(it){
                 val intentGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                 activityResultLauncher.launch(intentGallery)

             }else{

                Toast.makeText(this,"IZIN VERINIZ ",Toast.LENGTH_LONG).show()
             }
         }
     }

    }
















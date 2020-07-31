package com.common.recipe_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_add_update_recipe.*
import kotlinx.android.synthetic.main.activity_recipe_details.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddUpdateRecipe : AppCompatActivity() {

    var currentPath: String? = null
    val TAKE_PICTURE = 1
    val SELECT_PICTURE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_recipe)
        val title = title_et.text.toString()
        val type = type_et.text.toString()
        val desc = desc_et.text.toString()
        val ing = ing_et.text.toString()

        // Open camera
        camera_btn.setOnClickListener {
            dispatchCameraIntent()

        }
        // Open gallery
        gallery_btn.setOnClickListener {
            dispatchGalleryIntent()
        }

        // Save updated/new info to pass it to MainActivity
        val addUpdate_sp = getSharedPreferences("FROM_ADDUPDATE", Context.MODE_PRIVATE)
        submit_btn.setOnClickListener {

            val editor = addUpdate_sp.edit()
            editor.apply {
                putString("NEW_TITLE", title)
                putString("NEW_TYPE", type)
                putString("NEW_DESC", desc)
                putString("NEW_ING", ing)
            }.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK)
            try {
                val file = File(currentPath)
                val uri = Uri.fromFile(file)
                imageView2.setImageURI(uri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK)
            try {

                val uri = data!!.data
                imageView2.setImageURI(uri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
    }
    fun dispatchGalleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select image"), SELECT_PICTURE)

    }
    fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImage()

            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (photoFile != null) {
                var photoUri = FileProvider.getUriForFile(
                    this,
                    "com.common.recipe.fileprovider",
                    photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, TAKE_PICTURE)
            }
        }
    }

    fun createImage(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageName = "JPEG " + timeStamp + " "
        var storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image = File.createTempFile(imageName, ",jpg", storageDir)
        currentPath = image.absolutePath
        return image
    }
}

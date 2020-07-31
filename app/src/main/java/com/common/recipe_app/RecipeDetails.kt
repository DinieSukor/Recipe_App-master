package com.common.recipe_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_recipe_details.*
import java.io.File

private const val FILE_NAME = "photo.jpg"
private const val REQUEST_CODE = 42
private lateinit var photFile: File
class RecipeDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        // Take info from MainActivity
        val sp = getSharedPreferences("INFO_FROM_MAIN",Context.MODE_PRIVATE)

        
        imageView.setImageResource(sp.getInt("IMAGE",0))
        rd_title_tv.text = sp.getString("TITLE","")
        rd_type_tv.text = sp.getString("TYPE","")
        Ingredient.text = sp.getString("ING","")

        edit_btn.setOnClickListener {

            val intent = Intent(this, AddUpdateRecipe::class.java)
            startActivity(intent)
        }

    }

}
package com.common.recipe_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_update_recipe.*

class AddUpdateRecipe : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_recipe)

        var title = title_et.text.toString()
        var type = type_et.text.toString()
        var desc = desc_et.text.toString()
        var ing = ing_et.text.toString()

        val sharedPreferences = getSharedPreferences("NEW_DATA",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("NEW_TITLE",title)
            putString("NEW_TYPE",type)
            putString("NEW_DESC",desc)
            putString("NEW_ING", ing)
        }.apply()




        submit_btn.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}
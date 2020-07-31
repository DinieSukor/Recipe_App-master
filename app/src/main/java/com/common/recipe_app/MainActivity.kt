package com.common.recipe_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.card_layout.*


class MainActivity : AppCompatActivity(),Adapter.OnItemClickLister {

    var ProvidedRecipeList = ArrayList<RecipeData>()
    private val adapter = Adapter(ProvidedRecipeList,this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createView()

        // Create spinner
        var recipeType_spinner = resources.getStringArray(R.array.recipetypes)
        spinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, recipeType_spinner)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                // list all the existed recipe
                if (p2==0){
                    makeBreakfast()
                    makeLunch()
                    makeDinner()
                    makeDessert()
                    createView()

                }
                // list only breakfast
                else if (p2 == 1) {
                    ProvidedRecipeList.clear()
                    makeBreakfast()
                    // Create recycle view
                    createView()
                // list only lunch
                } else if (p2 == 2) {
                    ProvidedRecipeList.clear()
                    makeLunch()
                    // Create recycle view
                    createView()
                // list only dinner
                } else if (p2 == 3) {
                    ProvidedRecipeList.clear()
                    makeDinner()
                    // Create recycle view
                   createView()
                // list only dessert
                } else if (p2 == 4) {
                    ProvidedRecipeList.clear()
                    makeDessert()
                    // Create recycle view
                    createView()
                }
            }

        }
        // add fab
        add_fab.setOnClickListener {
            val open = Intent(this, AddUpdateRecipe::class.java)
            startActivity(open)

        }
        // delete
        // take info from AddUpdateRecipe

        /*
        val sharedPreferences = getSharedPreferences("NEW_DATA",Context.MODE_PRIVATE)
        val newTitle = sharedPreferences.getString("NEW_TITLE","")
        val newType = sharedPreferences.getString("NEW_TYPE","")
        val newDesc = sharedPreferences.getString("NEW_DESC","")
        val newIng = sharedPreferences.getString("NEW_ING","")
        Toast.makeText(this,newDesc,Toast.LENGTH_SHORT).show()
        //ProvidedRecipeList.add(RecipeData(R.drawable.br_pancake,newTitle,newType,newDesc,newIng))
        */





        /*
        var bundle = this.intent!!.extras

        var new_title = bundle!!.getString("TITLE").toString()
        var new_type = bundle!!.getString("TITLE").toString()
        var new_desc = bundle!!.getString("DESC").toString()
        var new_ing = bundle!!.getString("ING").toString()
        //TODO add new for image
        // create new card of recipe
        //ProvidedRecipeList.add(RecipeData(0,new_title,new_type,new_desc,new_ing))
        */






    }


    // created existed recipe (pre-populate data)
    // Breakfast
    fun makeBreakfast(){

        ProvidedRecipeList.add(RecipeData(R.drawable.br_pancake,"Pancake","Breakfast","Fluffy & Easy To Make"
            ,"Ingredients: Love and passion \nSteps:Mix them together "))
        ProvidedRecipeList.add(RecipeData(R.drawable.br_roti_canai,"Roti Canai","Breakfast","Hot Roti & Kari Is The Bomb"
            ,"Ingredients: Love and passion \nSteps:Mix them together "))
    }
    // Lunch
    fun makeLunch(){

        ProvidedRecipeList.add(RecipeData(R.drawable.lunch_mee_sup,"Mee Soup","Lunch","Hot Soup With Yummy Mee"
            ,"Ingredients: Love and passion \nSteps:Mix them together "))
    }
    // Dinner
    fun makeDinner(){
        ProvidedRecipeList.add(RecipeData(R.drawable.dinner_kuey_tiow_goreng,"Fried Kuey Tiow","Dinner"," " +
                "Fav Of All People","Ingredients: Love and passion \nSteps:Mix them together "))
    }

    // Dessert
    fun makeDessert(){
       ProvidedRecipeList.add(RecipeData(R.drawable.dessert_ic_pie,"Ice Cream Pie","Dessert","Best Enjoyed With Family"
           ,"Ingredients: Love and passion \nSteps:Mix them together "))
       ProvidedRecipeList.add(RecipeData(R.drawable.dessert_lava_cake,"Lava Cake","Dessert","Slurpy Sweet Choc Lava"
           ,"Ingredients: Love and passion\nSteps:Mix them together "))

    }

    // Apply adapter to recycle view
    fun createView(){


        recycler_view.adapter = Adapter(ProvidedRecipeList,this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        val clickedItem = ProvidedRecipeList[position]
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,RecipeDetails::class.java)
        // pass infos to RecipeDetail class
        val sp = getSharedPreferences("INFO_FROM_MAIN",Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.apply{
            putInt("IMAGE",clickedItem.imageResource)
            putString("TITLE",clickedItem.title)
            putString("TYPE",clickedItem.type)
            putString("ING",clickedItem.ing)
        }.apply()


        startActivity(intent)

    }
}




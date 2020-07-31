package com.common.recipe_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val list: List<RecipeData>,private val listener:OnItemClickLister):
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemVIew = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,
            parent,false)

        return ViewHolder(itemVIew)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.title.text = currentItem.title
        holder.type.text = currentItem.type
        holder.desc.text = currentItem.desc


    }

    override fun getItemCount() = list.size



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val title: TextView = itemView.findViewById(R.id.title_tv)
        val type: TextView = itemView.findViewById(R.id.type_tv)
        val desc: TextView = itemView.findViewById(R.id.desc_tv)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface  OnItemClickLister{
        fun onItemClick(position: Int)
    }

}
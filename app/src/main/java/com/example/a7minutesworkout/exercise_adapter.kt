package com.example.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemLevelBinding

class exercise_adapter(val item: ArrayList<exercises>) :
    RecyclerView.Adapter<exercise_adapter.view_Adapter>() {
    class view_Adapter(binding: ItemLevelBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvitem = binding.recleText


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_Adapter {
        return view_Adapter(ItemLevelBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: view_Adapter, position: Int) {
        val model: exercises = item[position]
        var h= holder.tvitem.text
        holder.tvitem.text = model.get_id().toString()
        when {
            model.get_selected() -> {
                   holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.back_ground_item_level_slected)
                holder.tvitem.setTextColor(Color.parseColor("#212121"))

            }
            model.get_complete() -> {
                holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.back_ground_item_level_complet)
                holder.tvitem.setTextColor(Color.parseColor("#ffffff"))


            }
            else -> {
                holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.back_ground_item_level)
                holder.tvitem.setTextColor(Color.parseColor("#212121"))


            }
        }

    }

    override fun getItemCount(): Int {
        return item.size
    }


}
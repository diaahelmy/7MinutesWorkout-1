package com.example.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemhistoryBinding

class history_adapter(private var items: ArrayList<String>) :
    RecyclerView.Adapter<history_adapter.viewHolder>() {


    class viewHolder(binding: ItemhistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val llhistory = binding.llhistory

        val tvpostion = binding.tvpostion
        val tvitem = binding.tvitem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
 return viewHolder(binding = ItemhistoryBinding.inflate(LayoutInflater.from(parent.context),
     parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
      val data :String=items.get(position)
        holder.tvpostion.text= (position+1).toString()
        holder.tvitem.text=data

        if (position %2 == 0){
            holder.llhistory.setBackgroundColor(Color.parseColor("#ffffff"))
        }
        else{
            holder.llhistory.setBackgroundColor(Color.parseColor("#EBEBEB"))

        }



    }

    override fun getItemCount(): Int {
        return items.size
    }
}
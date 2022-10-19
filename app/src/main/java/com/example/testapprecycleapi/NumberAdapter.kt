package com.example.testapprecycleapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapprecycleapi.databinding.NumberDescItemBinding

class NumberAdapter(val listener: Listener): RecyclerView.Adapter<NumberAdapter.NumberHolder>() {

    val numberList = ArrayList<Number>()

    class NumberHolder(item : View): RecyclerView.ViewHolder(item){
        val binding = NumberDescItemBinding.bind(item)

        fun bind(number: Number,listener: Listener) = with(binding){
            descriptionTv.text = number.description
            itemView.setOnClickListener{
               listener.onClick(number)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.number_desc_item, parent, false)
        return NumberHolder(view)
    }

    override fun onBindViewHolder(holder: NumberHolder, position: Int) {
        holder.bind(numberList[position], listener)
    }

    override fun getItemCount(): Int {
        return numberList.size
    }

    fun addDesc(number: Number){
        numberList.add(number)
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClick(number: Number){

        }
    }

}
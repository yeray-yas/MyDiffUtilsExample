package com.yerayyas.mydiffutilsexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yerayyas.mydiffutilsexample.databinding.ItemSuperheroBinding

class SuperheroAdapter(
    private var list: List<Superhero>,
    private val onItemRemove: (Superhero) -> Unit
) :
    RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(newList: List<Superhero>) {
        val superheroDiff = SuperheroDiffUtil(list, newList)
        val result = DiffUtil.calculateDiff(superheroDiff)
        list = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        // This is the function that will be called to inflate the layout for each row
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return SuperheroViewHolder(view)
    }

    override fun getItemCount(): Int = list.size // // This is the function that will be called to get the number of rows

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        // This is the function that will be called by the RecyclerView to display the data at the specified position.
        holder.bind(list[position], onItemRemove)
    }
}

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superhero: Superhero, onItemRemove: (Superhero) -> Unit) {
        with(binding) {
            tvSuperheroName.text = superhero.name
            tvId.text = superhero.id
            tvSuperheroName.setOnClickListener { onItemRemove(superhero) }
        }
    }
}
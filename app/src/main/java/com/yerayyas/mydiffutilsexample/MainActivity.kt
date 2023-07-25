package com.yerayyas.mydiffutilsexample

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.yerayyas.mydiffutilsexample.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var superheroes = listOf(
        Superhero("YerayYas", "1", "yerayyas.com"),
        Superhero("PeteElAnguila", "2", "yerayyas.com"),
        Superhero("FlutterCry", "3", "yerayyas.com"),
        Superhero("PepeMonagas", "4", "yerayyas.com"),
        Superhero("CerezoBoy", "5", "yerayyas.com"),
    )
    private lateinit var superheroAdapter: SuperheroAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createSuperhero()

        superheroAdapter = SuperheroAdapter(superheroes) {
            superheroes = superheroes.minus(it)
            // superheroAdapter.notifyDataSetChanged()
            superheroAdapter.updateList(superheroes)
            Log.d("ERASED", superheroes.toString())
            showSizeOfSuperheroesList()
        }

        binding.rvRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = superheroAdapter
        }
    }

    private fun createSuperhero() {
        binding.btnAddSuperhero.setOnClickListener {
            val random = Random.nextInt(10000).toString()
            val superhero = Superhero("FlutterCry $random", random, "yerayyas.com")
            superheroes = superheroes.plus(superhero)
            // superheroAdapter.notifyDataSetChanged()
            superheroAdapter.updateList(superheroes)
            Log.d("CREATES", superheroes.toString())
            showSizeOfSuperheroesList()

        }
    }

    private fun showSizeOfSuperheroesList() {
        Log.d("TAMA", superheroes.size.toString())
    }
}
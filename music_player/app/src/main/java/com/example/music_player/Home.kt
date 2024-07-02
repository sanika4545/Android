package com.example.music_player

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.music_player.adapter.CategoryAdapter
import com.example.music_player.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter : CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCategories()
    }
    private fun getCategories(){
        FirebaseFirestore.getInstance().collection("categories")
            .get().addOnSuccessListener {
                val categoryList=it.toObjects(CategoryModel::class.java)
                setUpCategoryRecyclerView(categoryList)
            }
    }

    private fun setUpCategoryRecyclerView(categoryList: List<CategoryModel>) {
        categoryAdapter = CategoryAdapter(categoryList)


    }
}

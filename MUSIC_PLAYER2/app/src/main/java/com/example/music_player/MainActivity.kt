package com.example.music_player

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.music_player.Models.CategoryModel
import com.example.music_player.Models.SongModels
import com.example.music_player.databinding.ActivityMainBinding
import com.example.music_player.adapter.CategoryAdapter
import com.example.music_player.adapter.SectionSongListAdapter
import com.example.music_player.databinding.SectionItemRecyclerRowBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObjects

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCategories()
        setupSection("section_1",binding.section1,binding.section1Title,binding.section1RecyclerView)
        setupSection("section_2",binding.section2,binding.section2Title,binding.section2RecyclerView)
        setupSection("section_3",binding.section3,binding.section3Title,binding.section3RecyclerView)
        setupMostlyPlayed("Most_Played",binding.mostPlayed,binding.mostPlayedTitle,binding.mostPlayedRecyclerView)

        binding.imageView3.setOnClickListener{
            showPopupMenu()
        }
    }

    private fun showPopupMenu(){
        val popupMenu = PopupMenu(this,binding.imageView3)
        val inflator = popupMenu.menuInflater
        inflator.inflate(R.menu.option,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.logout -> {
                    logout()
                    true
                }
            }
            false
        }
    }

    private fun logout(){
        MyExoplayer.getInstance()?.release()
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,Login1::class.java))
        finish()
    }
    override fun onResume(){
        super.onResume()
        showPlayerView()
    }


    @SuppressLint("SetTextI18n")
    private fun showPlayerView() {
        binding.playerView.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
        MyExoplayer.getCurrentSong()?.let {
            binding.playerView.visibility = android.view.View.VISIBLE
            binding.songTitleTextView.text = "Now Playing : "+ it.title
            Glide.with(binding.songCoverImageView).load(it.coverPageUrl)
                .apply(
                    RequestOptions().transform(RoundedCorners(32))
                ).into(binding.songCoverImageView)
        } ?: run {
            binding.playerView.visibility = android.view.View.GONE
        }
    }

    private fun getCategories() {
        FirebaseFirestore.getInstance().collection("categories")
            .get().addOnSuccessListener {
                val categoryList = it.toObjects(CategoryModel::class.java)
                setUpCategoryRecyclerView(categoryList)
            }

    }

    private fun setUpCategoryRecyclerView(categoryList: List<CategoryModel>) {
        categoryAdapter = CategoryAdapter(categoryList)
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.categoriesRecyclerView.adapter = categoryAdapter


    }

    //Sections
    private fun setupSection(
        id: String,
        mainLayout: RelativeLayout,
        titleView: TextView,
        recyclerView: RecyclerView
    ) {
        FirebaseFirestore.getInstance().collection("sections")
            .document(id)
            .get().addOnSuccessListener {
                val section = it.toObject(CategoryModel::class.java)
                section?.apply {
                    mainLayout.visibility = android.view.View.VISIBLE
                    titleView.text = name
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false
                    )
                    recyclerView.adapter = SectionSongListAdapter(song)
                    mainLayout.setOnClickListener {
                        songList.categoryModel = section
                        startActivity(Intent(this@MainActivity, songList::class.java))
                    }
                }

            }
    }
    //most played
    private fun setupMostlyPlayed(id: String,mainLayout: RelativeLayout,titleView: TextView,recyclerView: RecyclerView){
        FirebaseFirestore.getInstance().collection("sections")
            .document(id)
            .get().addOnSuccessListener {
                //get Most played song
                FirebaseFirestore.getInstance().collection("song")


                .orderBy("count",Query.Direction.DESCENDING)
                    .limit(5)
                    .get().addOnSuccessListener { songListSnapshot ->
                        val songsModeList =songListSnapshot.toObjects<SongModels>()
                        val songsIdList=songsModeList.map{
                            it.id
                        } .toList()
                        val section =it.toObject(CategoryModel::class.java)
                        section?.apply {
                            section.song =songsIdList
                            mainLayout.visibility=View.VISIBLE
                            titleView.text=name
                            recyclerView.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                            recyclerView.adapter= SectionSongListAdapter(song)
                            mainLayout.setOnClickListener{
                                songList.categoryModel=section
                                startActivity(Intent(this@MainActivity,songList::class.java))
                            }


                        }



                    }

            }

    }

}

package com.example.music_player.Models

data class CategoryModel(val name:String,
                         val coverUrl:String,
                         var song :List<String>)
{
    constructor():this("", "", listOf())

}

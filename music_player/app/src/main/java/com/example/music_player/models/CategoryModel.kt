package com.example.music_player

data class CategoryModel(val name:String,
                         val coverUrl:String)
{
    constructor():this("", "")
}
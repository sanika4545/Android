package com.example.music_player.Models

data class SongModels(

    val id :String,
    val title : String,
    val subtitle :String,
    val Url :String,
    val coverPageUrl:String,

    ){
    constructor():this("","","","","")
}

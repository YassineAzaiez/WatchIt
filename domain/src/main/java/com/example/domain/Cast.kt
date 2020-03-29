package com.example.domain

import java.io.Serializable

/**
 * Created by yassine 18/02/20 .
 */

 data class Cast (
    val cast_id : Int = 0 ,
    val character: String? = "" ,
    val credit_id : String? = "" ,
    val gender : Int = 0 ,
    val id : Int = 0 ,
    val name : String? = "" ,
    val order : Int = 0 ,
    val profile_path : String? = ""

) : Serializable
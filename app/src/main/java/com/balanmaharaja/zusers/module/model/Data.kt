package com.balanmaharaja.zusers.module.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Data(
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("last_name")
    var lastName: String,
) : Serializable
{
    fun getFirstChar(): String {
        return if (firstName.isNotEmpty()) {
            firstName[0].plus((lastName[0]).toString())
        }
        else {
            ""
        }
    }

    fun getFullName(): String {
        return if (lastName.isNotEmpty()) {
            "$firstName $lastName"
        } else {
            firstName
        }
    }
}

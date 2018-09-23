package com.factory.andre.factoryinmobile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Auth(
        @SerializedName("name")
        @Expose
        var name: String?,

        @SerializedName("login")
        @Expose
        var login: String?,

        @SerializedName("pass")
        @Expose
        var password: String?) {


}
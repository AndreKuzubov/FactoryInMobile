package com.factory.andre.factoryinmobile.model

import com.factory.andre.factoryinmobile.model.base.IBaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Auth(
        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("login")
        @Expose
        var login: String? = null,

        @SerializedName("pass")
        @Expose
        var password: String? = null,

        override var timestamp: Long? = null
) : IBaseModel {


}
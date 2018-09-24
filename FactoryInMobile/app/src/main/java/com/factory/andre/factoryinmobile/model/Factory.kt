package com.factory.andre.factoryinmobile.model

import com.factory.andre.factoryinmobile.model.base.IBaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Factory(
        @SerializedName("factory")
        @Expose
        var name: String?,

        @SerializedName("pumpes")
        @Expose
        var pumpes: List<Pump>?,

        @SerializedName("tankers")
        @Expose
        var tanks: List<Tank>?,

        override var timestamp: Long?
) : IBaseModel {
}
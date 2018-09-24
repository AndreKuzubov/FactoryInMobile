package com.factory.andre.factoryinmobile.model

import com.factory.andre.factoryinmobile.model.base.IFactoryItemsModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Tank(
        @SerializedName("factory")
        @Expose
        override var factory: String? = null,

        @SerializedName("tag")
        @Expose
        override var tag: String? = null,

        @SerializedName("status")
        @Expose
        var status: String? = null,


        @SerializedName("volume")
        @Expose
        var volume: Float? = null,

        @SerializedName("fill")
        @Expose
        var fill: Float? = null,


        override var timestamp: Long? = null
) : IFactoryItemsModel {


}
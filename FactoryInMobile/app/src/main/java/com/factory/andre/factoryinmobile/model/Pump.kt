package com.factory.andre.factoryinmobile.model

import com.factory.andre.factoryinmobile.model.base.IFactoryItemsModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pump(
        override var factory: String? = null,

        @SerializedName("tag")
        @Expose
        override var tag: String? = null,


        @SerializedName("voltage")
        @Expose
        var voltage: Float? = null,

        @SerializedName("allowVoltage")
        @Expose
        var allowVoltage: Float? = null,


        @SerializedName("turnovers")
        @Expose
        var turnovers: Float? = null,

        @SerializedName("allowTurnovers")
        @Expose
        var allowTurnovers: Float? = null,

        @SerializedName("turnOn")
        @Expose
        var turnOn: Boolean? = null,

//        TODO add Pump.status to restApi.to realm
        @SerializedName("status")
        @Expose
        var status: String? = null,

        override var timestamp: Long? = null

) : IFactoryItemsModel {
}
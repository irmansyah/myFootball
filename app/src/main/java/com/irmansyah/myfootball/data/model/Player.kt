package com.irmansyah.myfootball.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PlayerResponse(@SerializedName("player")
                          @Expose
                          var players: List<Player>? = null)

@Parcelize
class Player(@SerializedName("strPlayer")
             @Expose
             var strPlayer: String? = null,

             @SerializedName("strDescriptionEN")
             @Expose
             var strDescriptionEN: String? = null,

             @SerializedName("strPosition")
             @Expose
             var strPosition: String? = null,

             @SerializedName("strThumb")
             @Expose
             var strThumb: String? = null,

             @SerializedName("strCutout")
             @Expose
             var strCutout: String? = null
) : Parcelable
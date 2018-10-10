package com.irmansyah.myfootball.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class TeamResponse(val teams: List<Team>)

@Parcelize
class Team(@SerializedName("idTeam")
           @Expose
           var idTeam: String? = null,

           @SerializedName("strTeam")
           @Expose
           var strTeam: String? = null,

           @SerializedName("strDescriptionEN")
           @Expose
           var strDescriptionEN: String? = null,


           @SerializedName("strTeamBadge")
           @Expose
           var strTeamBadge: String? = null

           ) : Parcelable
package ru.indefinitedream.moscowhotels.data

import com.google.gson.annotations.SerializedName

/**
 * Model class to store api data
 */
class Hotel {

    @SerializedName("Cells")
    var cells : Cells? = null
}

class Cells {

    @SerializedName("FullName")
    var name : String? = null

    @SerializedName("AdmArea")
    var admArea : String? = null

    @SerializedName("District")
    var district : String? = null

    @SerializedName("Address")
    var address : String? = null

    @SerializedName("Email")
    var email : String? = null

    @SerializedName("WebSite")
    var webSite : String? = null

    @SerializedName("Categorization")
    var category : String? = null

    var stars : Int? = null

    @SerializedName("ContactPhone")
    var phone : Array<Phone>? = null

    @SerializedName("geoData")
    var geo : Geo? = null

}

class Phone {

    @SerializedName("PublicPhone")
    var public : String? = null
}

class Geo {

    @SerializedName("coordinates")
    var coordinates : Array<Double>? = null
}
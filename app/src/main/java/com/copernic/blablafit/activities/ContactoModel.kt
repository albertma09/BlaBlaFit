package com.copernic.blablafit.activities

data class ContactoModel(val name:String, val series:String,val repeticiones:String, private val image:String){
    //Atributos rutina de usuario
    var contactName: String? = null
    var contactSeries: String?=null
    var contactRepeticiones: String?=null
    var contactImage: String? = null


    init {
        this.contactName = name
        this.contactSeries= series
        this.contactRepeticiones = repeticiones
        this.contactImage= image
    }


}

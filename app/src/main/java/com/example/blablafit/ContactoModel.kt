package com.example.blablafit

data class ContactoModel(val name:String, val description:String, private val image:String){
    var contactName: String? = null
    var contactDescription: String?=null
    var contactImage: String? = null

    init {
        this.contactName = name
        this.contactDescription= description
        this.contactImage= image
    }
}

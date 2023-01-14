package com.example.blablafit.activities

data class Alimentacion(val alimento:String, val cantidad:String){
    var contactAlimento: String? = null
    var contactCantidad: String?=null


    init {
        this.contactAlimento = alimento
        this.contactCantidad= cantidad

    }


}

package com.example.blablafit

class ContactoModel(val name: String) {

    companion object {
        private var lastContactId = 0
        fun createContactsList(numContacts: Int) : ArrayList<ContactoModel> {
            val contacts = ArrayList<ContactoModel>()
            for (i in 1..numContacts) {
                contacts.add(ContactoModel("Person " + ++lastContactId))
            }
            return contacts
        }
    }
}
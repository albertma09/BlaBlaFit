package com.copernic.blablafit.Utils

import android.content.Context
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

object UtilsFunctions {

    fun checkMail(mail: String, password: String, view: View): Boolean {
        var mailCheck: Boolean = false
        var passwordCheck: Boolean = false
        var pattern_password: Regex =
            ("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}\$").toRegex()

        var pattern_mail: Regex = ("^[A-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_" +
                "`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
                "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\$").toRegex()


        if (pattern_mail.matches(mail)) {
            if (pattern_password.matches(password)) {
                Log.i("Password", "Correcto")
                passwordCheck = true
            } else {
                Log.i("Password", "No valido")
                Snackbar.make(
                    view,
                    "La contraseña debe iniciar con mayuscula y minimo 1 digito",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            mailCheck = true
        } else {
            Log.i("Correo", "No valido")
            Snackbar.make(view, "El correo no es valido", Snackbar.LENGTH_SHORT).show()
        }



        if ((mailCheck == true) && (passwordCheck == true)) {
            return true

        }





        return false
    }

    fun showAlertConnect(ctx: Context) {
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle("Correct")
        builder.setMessage("Se ha registrado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showAlert(ctx: Context) {
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
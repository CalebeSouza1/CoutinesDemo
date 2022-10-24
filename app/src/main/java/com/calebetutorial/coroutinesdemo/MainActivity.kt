package com.calebetutorial.coroutinesdemo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    var customProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExecute: Button = findViewById(R.id.btn_execute)
        btnExecute.setOnClickListener {
            showProgressDialog()
            lifecycleScope.launch {
                execute("Task executed successfully")
            }


        }
    }

    private suspend fun execute(result:String){
        withContext(Dispatchers.IO){
            for(i in 1..30000){
                Log.e("delay: ", " " + 1 )
            }
            runOnUiThread {
                cancelProgressDialog()
                Toast.makeText(
                    this@MainActivity, result,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun cancelProgressDialog() {
        if(customProgressDialog != null) {
            customProgressDialog?.dismiss()
            customProgressDialog = null
        }
    }

    private fun showProgressDialog() {
        customProgressDialog = Dialog(this@MainActivity)

        /*Defina então o conteúdo da layout resource.
        O resource será inflated, adding all top-level views da tela.*/
        customProgressDialog?.setContentView(R.layout.dialog_custom_progress)

        //inicia a caixa de dialogo e exibe na tela.
        customProgressDialog?.show()
    }

}
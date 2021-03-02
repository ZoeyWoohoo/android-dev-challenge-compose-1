package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevchallenge.model.CatBean
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.view.CatDetailScreen

class CatDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cat = intent.getParcelableExtra<CatBean>(CAT)

        if (cat == null) {
            Toast.makeText(this, "Oops, something going down.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setContent {
            MyTheme {
                CatDetailScreen(cat = cat, onBackPressed = { onBackPressed() })
            }
        }
    }

    companion object {
        const val CAT = "cat"

        fun start(context: Context, cat: CatBean) {
            context.startActivity(
                Intent(context, CatDetailActivity::class.java).also {
                    it.putExtra(CAT, cat)
                }
            )
        }
    }
}
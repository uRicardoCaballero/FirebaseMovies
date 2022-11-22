package com.example.firebasemovies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.firebasemovies.LoginActivity
import com.example.firebasemovies.R
import com.google.firebase.auth.FirebaseAuth
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import com.example.firebasemovies.ui.movies.MovieList
import com.example.firebasemovies.ui.theme.AppTheme
import com.example.firebasemovies.data.network.Resource
import com.example.firebasemovies.ui.common.FullScreenProgressbar
import com.example.firebasemovies.ui.common.toast
import androidx.compose.runtime.collectAsState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var logoutBtn: Button
    private lateinit var updatePass: Button

    private val viewModel by viewModels<MainViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }

        setContentView(R.layout.activity_main)
        setContent {
            val context = LocalContext.current
            val movies = viewModel.movies.collectAsState()

            AppTheme {
                movies.value?.let {
                    when (it) {
                        is Resource.Failure -> {
                            context.toast(it.exception.message!!)
                        }
                        Resource.Loading -> {
                            FullScreenProgressbar()
                        }
                        is Resource.Success -> {
                            MovieList(it.result)
                        }
                    }
                }
            }
        }

//no tocar, logout en proceso

//*logoutBtn = findViewById(R.id.logout_btn)
//
//
//        logoutBtn.setOnClickListener{
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        } *//

    }
}
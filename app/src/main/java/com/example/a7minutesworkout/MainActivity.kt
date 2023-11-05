package com.example.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fram.setOnClickListener {
         val intent=Intent(this,Exercise::class.java)
            startActivity(intent)

        }
        binding.bIMf.setOnClickListener{
            val intent=Intent(this,ActivityBIM::class.java)
            startActivity(intent)

        }
        binding.flHISTORY.setOnClickListener{
            val intent=Intent(this,History_Activity_main::class.java)
            startActivity(intent)

        }
    }
}
package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class finish : AppCompatActivity() {
    lateinit var binding: ActivityFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.finish.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val dao = (application as WorkoutApp).db.historyDao()
        addDATAtodatabase(dao)
    }

    private fun addDATAtodatabase(historyDao: HistoryDao) {
        val c = Calendar.getInstance()
        val datatime = c.time

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val data = sdf.format(datatime)



        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(data))

        }


    }
}
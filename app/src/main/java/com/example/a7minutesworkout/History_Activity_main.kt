package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.databinding.ActivityHistoryMainBinding
import kotlinx.coroutines.launch
import java.util.ArrayList

class History_Activity_main : AppCompatActivity() {
    lateinit var binding: ActivityHistoryMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHistoryMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarhistory)
        if (supportActionBar !=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="HISTORY"

        }
        binding.toolbarhistory.setNavigationOnClickListener {
            onBackPressed()
        }
        val dao =(application as WorkoutApp).db.historyDao()
        allfetch(dao)

    }



    fun allfetch(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.allfetchqueue().collect{alldata ->
                binding.rvhistory.layoutManager=LinearLayoutManager(this@History_Activity_main)
                  val datas=ArrayList<String>()
                  for (data in alldata){
                      datas.add(data.data)

                  }
                binding.rvhistory.adapter=history_adapter(datas)


            }

        }


    }


    override fun onDestroy() {
        supportActionBar==null

        super.onDestroy()
    }
}
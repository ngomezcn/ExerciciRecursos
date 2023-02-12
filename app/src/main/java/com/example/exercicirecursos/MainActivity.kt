package com.example.exercicirecursos

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicirecursos.Models.Mineral
import com.example.exercicirecursos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var metall : Mineral
    lateinit var cristall : Mineral
    lateinit var deuteri : Mineral

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        metall = Mineral(binding.metallRawNum, binding.metallLingotNum,  1000)
        cristall = Mineral(binding.cristallRawNum, binding.cristallLingotNum,  2000)
        deuteri = Mineral(binding.deuteriRawNum, binding.deuteriLingotNum, 3000)

        CoroutineScope(Dispatchers.Default).launch {
            metall.startMining()
        }
        CoroutineScope(Dispatchers.Default).launch {
            cristall.startMining()
        }
        CoroutineScope(Dispatchers.Default).launch {
            deuteri.startMining()
        }

        binding.metallRaw.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                metall.collect()
            }
        }

        binding.cristallRaw.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                cristall.collect()
            }
        }

        binding.deuteriRaw.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                deuteri.collect()
            }
        }
    }
}


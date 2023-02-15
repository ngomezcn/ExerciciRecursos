package com.example.exercicirecursos

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicirecursos.Models.Resources
import com.example.exercicirecursos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var metall : Resources
    lateinit var cristall : Resources
    lateinit var deuteri : Resources


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val miningAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.mining);
        val shakeAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.shake);

        metall = Resources(binding.metallRawNum, binding.metallLingotNum,  1000)
        binding.mineroMetall.startAnimation(miningAnim);

        cristall = Resources(binding.cristallRawNum, binding.cristallLingotNum,  2000)
        binding.mineroCristall.startAnimation(miningAnim);

        deuteri = Resources(binding.deuteriRawNum, binding.deuteriLingotNum, 3000)
        binding.mineroDeuteri.startAnimation(miningAnim);

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
            binding.metallRaw.animation = shakeAnim
            CoroutineScope(Dispatchers.Default).launch {
                metall.collect()
            }
        }

        binding.cristallRaw.setOnClickListener {
            binding.cristallRaw.animation = shakeAnim
            CoroutineScope(Dispatchers.Default).launch {
                cristall.collect()
            }
        }

        binding.deuteriRaw.setOnClickListener {
            binding.deuteriRaw.animation = shakeAnim
            CoroutineScope(Dispatchers.Default).launch {
                deuteri.collect()
            }
        }
    }
}


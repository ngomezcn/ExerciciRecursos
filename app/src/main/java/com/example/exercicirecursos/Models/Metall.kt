package com.example.exercicirecursos.Models

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.exercicirecursos.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Mineral(
    val rawCount: TextView,
    val collectedCount: TextView,
    val generationTime: Long
)
{
    val channel = Channel<String>(2);
    var counter = 0
    val collected  = mutableListOf<String>()

    suspend fun startMining()
    {
        while(true){
            delay(generationTime)
            counter ++;

            withContext(Dispatchers.Main){
                rawCount.text = counter.toString()
            }

            channel.send("M")
        }
    }

    suspend fun collect()
    {
        while(!channel.isEmpty)
        {
            collected.add(channel.receive());
        }
        withContext(Dispatchers.Main) {
            collectedCount.text = collected.size.toString()
            rawCount.text = "0"
        }
        counter = 0;
    }
}
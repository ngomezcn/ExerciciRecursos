package com.example.exercicirecursos.Models

import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Resources(
    val rawCount: TextView,
    val collectedCount: TextView,
    val generationTime: Long
)
{
    val channel = Channel<String>(3);
    var counter = 0
    val collected  = mutableListOf<String>()

    suspend fun startMining()
    {
        while(true){
            withContext(Dispatchers.Main){
                rawCount.text = counter.toString()
            }
            channel.send("M")
            delay(generationTime)
            counter++
        }
    }

    suspend fun collect()
    {
        if(counter > 0)
        {
            collected.add(channel.receive())
            counter --
            withContext(Dispatchers.Main) {
                collectedCount.text = collected.size.toString()
                rawCount.text = counter.toString()
            }
        }
    }
}
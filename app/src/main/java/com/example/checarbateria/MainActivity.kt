package com.example.checarbateria

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvStatus: TextView
    private lateinit var botao: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvStatus = findViewById(R.id.tvStatus)
        this.botao = findViewById(R.id.botao)
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            this.registerReceiver(null, ifilter)
        }
       this.botao.setOnClickListener {
           val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
           val statusCarregamento: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING

           Log.i("CHECA_BATERIA", statusCarregamento.toString())
           if (statusCarregamento) {
               this.tvStatus.text = "Carregando!"
           } else {
               this.tvStatus.text = "Não está carregando!"
           }
       }
    }
}

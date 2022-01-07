package com.zephyr.message

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.zephyr.annotation.Route
import com.zephyr.message.databinding.ActivityMessageDetailBinding

@Route(path = "/test/message_detail")
class MessageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMessageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MessageDetailActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
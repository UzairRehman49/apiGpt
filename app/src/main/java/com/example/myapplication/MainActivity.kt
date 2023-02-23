package com.example.myapplication

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.aallam.openai.api.ExperimentalOpenAI
import com.aallam.openai.api.exception.OpenAIHttpException
import com.aallam.openai.api.image.ImageCreationURL
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.image.ImageURL
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import com.squareup.picasso.Picasso
import io.ktor.client.plugins.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalOpenAI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val imageView = findViewById<ImageView>(R.id.imageView)
        var images: List<ImageURL>? = null
        val button = findViewById<Button>(R.id.button)
        val config = OpenAIConfig("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")
        val openAI = OpenAI(config)


        button.setOnClickListener(View.OnClickListener {
            runBlocking {
                    images = openAI.image( // or openAI.imageJSON
                        creation = ImageCreationURL(
                            prompt = editText.text.toString(),
                            n = 1,
                            size = ImageSize.is1024x1024
                        )
                    )


            }
            Picasso.get().load(images?.get(0)?.url).resize(50,50).into(imageView);}

        )

        }
    }



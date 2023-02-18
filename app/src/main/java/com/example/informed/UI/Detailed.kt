package com.example.informed.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.informed.R
import com.example.informed.Utility.Constant
import com.example.informed.databinding.ActivityDetailedBinding

class Detailed : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        val title = intent.getStringExtra(Constant.TITLE)
        val subHeading = intent.getStringExtra(Constant.SUBHEADING)
        val url_img = intent.getStringExtra(Constant.ARTICLE_IMG)
        val content = intent.getStringExtra(Constant.CONTENT)
        val url_link = intent.getStringExtra(Constant.URL_LINK)!!
        val publishAt = intent.getStringExtra(Constant.PUBLISH_AT)


        binding.articleTitle.text = title
        binding.articleImg.load(url_img){
            placeholder(R.drawable.defaultimg)
        }

        binding.articalSubheading.text = subHeading
        binding.articleContent.text = content
        binding.publishAt.text = publishAt

        binding.loadMore.setOnClickListener {


                if (!url_link.isEmpty()) {

                    val intent = Intent(this,Webview::class.java)
                    intent.putExtra(Constant.URL,url_link)
                    startActivity(intent)
                } else {

                    Toast.makeText(this,"Sorry URL is null!", Toast.LENGTH_SHORT).show()
                }




        }











    }
}
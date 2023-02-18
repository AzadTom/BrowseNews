package com.example.informed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.informed.Model.Article
import com.example.informed.R

class NewsAdapter(private val  context: Context) : PagingDataAdapter<Article,NewsAdapter.ArticalViewHolder>(Comparator) {


     class ArticalViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val newsimg = itemview.findViewById<ImageView>(R.id.newsimg)
        val title = itemview.findViewById<TextView>(R.id.title)
        val subheading = itemview.findViewById<TextView>(R.id.subheading)
        val publishAt = itemview.findViewById<TextView>(R.id.publistAt)
         val worldwide = itemview.findViewById<ImageView>(R.id.worldwide)


    }


   companion object{

       private val Comparator = object : DiffUtil.ItemCallback<Article>() {
           override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {

               return oldItem.url == newItem.url

           }

           override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {

               return oldItem == newItem
           }


       }


   }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticalViewHolder {


        return ArticalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news, parent, false)
        )

    }




    override fun onBindViewHolder(holder: ArticalViewHolder, position: Int) {


        val article = getItem(position)


        article?.let {

            holder.newsimg.load(article.urlToImage)
            {
                placeholder(R.drawable.defaultimg)
            }


            holder.title.text = article.title
            holder.subheading.text = article.description

            holder.publishAt.text = article.publishedAt!!.substring(0,4)

            holder.worldwide.setOnClickListener {

                onwebClickListener?.let {

                    it(article.url)

                }

            }

            holder.itemView.setOnClickListener {

                onItemClickListener?.let {

                    it(article)


                }

            }


        }




    }


    private var onItemClickListener: ((Article) -> Unit)? = null
    private var onwebClickListener : ((String)->Unit)?= null

    fun setonWebviewClickListener(listener: (String)-> Unit){

         onwebClickListener = listener

    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {

        onItemClickListener = listener
    }

}
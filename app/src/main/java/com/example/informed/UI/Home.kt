package com.example.informed.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.informed.Adapter.NewsAdapter
import com.example.informed.Adapter.NewsPageLoadStateAdapter
import com.example.informed.Model.Article
import com.example.informed.Utility.Constant
import com.example.informed.Utility.Constant.URL
import com.example.informed.ViewModel.NewsViewmodel
import com.example.informed.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var adapter: NewsAdapter

    @Inject
    lateinit var viewmodel: NewsViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setUpRecyclerView()

        breakingNews()

        binding.swipeLayout.setOnRefreshListener {


            updateData()


        }

        setupSearchBar()

















    }

    private  fun updateData() {

//        Toast.makeText(this,"it is working!",Toast.LENGTH_SHORT).show()

        viewmodel.breakingNewsList.observe(this){

            adapter.submitData(lifecycle,it)



        }

            binding.swipeLayout.isRefreshing = false


    }


    private fun setUpRecyclerView() {

        adapter = NewsAdapter(this)

        binding.newsRecycler.layoutManager = LinearLayoutManager(this)
        binding.newsRecycler.setHasFixedSize(true)
        binding.newsRecycler.adapter = adapter.withLoadStateHeaderAndFooter(
            header = NewsPageLoadStateAdapter(),
            footer = NewsPageLoadStateAdapter()
        )






        adapter.addLoadStateListener {  loadstate ->



            binding.newsRecycler.isVisible = loadstate.source.refresh is LoadState.NotLoading
            binding.progressBar2.isVisible = loadstate.source.refresh is LoadState.Loading
            handlerror(loadstate)



        }



        adapter.setonWebviewClickListener { url ->


            if (!url.isBlank())
            {

                val intent = Intent(this,Webview::class.java)
                intent.putExtra(URL,url)
                startActivity(intent)
            }
            else
            {

                Toast.makeText(this,"Sorry URL is null!",Toast.LENGTH_SHORT).show()
            }




        }

        adapter.setOnItemClickListener {artical->

            val intent = Intent(this,Detailed::class.java)

            intent.putExtra(Constant.TITLE,artical.title)
            intent.putExtra(Constant.SUBHEADING,artical.description)
            intent.putExtra(Constant.ARTICLE_IMG,artical.urlToImage)
            intent.putExtra(Constant.URL_LINK,artical.url)
            intent.putExtra(Constant.CONTENT,artical.content)
            intent.putExtra(Constant.PUBLISH_AT, artical.publishedAt.substring(0,4))
            startActivity(intent)








        }



    }

    private fun handlerror(loadstate: CombinedLoadStates) {


        val errorState = loadstate.source.append as? LoadState.Error
            ?:loadstate.source.prepend as? LoadState.Error

        errorState?.let {

//            Toast.makeText(this,"Limit is completed!",Toast.LENGTH_SHORT).show()



        }


    }


    private fun setupSearchBar(){


        binding.search.setOnClickListener {

            binding.search.isCursorVisible = true

        }

          binding.search.addTextChangedListener {


              if (it.isNullOrEmpty())
              {
                  breakingNews()
                  binding.topheadline.visibility = View.VISIBLE
              }


          }

            binding.search.setOnEditorActionListener { _, actionId, _ ->


                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {

                    val text = binding.search.text.trim().toString()
                    search(text)



                }

                true




            }








    }



    private fun search(query: String) {

            binding.search.isCursorVisible = false
            hidekeyboard()

            viewmodel.searchForNews(query).observe(this) {

                if (it != null){

                    adapter.submitData(lifecycle, it)
                }



            }

        if (binding.search.text.isNullOrEmpty())
        {
            breakingNews()
            binding.topheadline.visibility = View.VISIBLE
        }

        }






    private fun hidekeyboard() {


        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var currentFocus = this.currentFocus

        if (currentFocus == null)
        {
            currentFocus = View(this)
        }

        imm.hideSoftInputFromWindow(currentFocus.windowToken,0)

    }

    private fun breakingNews(){




        binding.topheadline.visibility = View.VISIBLE
        viewmodel.breakingNewsList.observe(this) {
            adapter.submitData(lifecycle, it)


        }







    }

}






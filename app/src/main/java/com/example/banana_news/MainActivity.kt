package com.example.banana_news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.banana_news.api.ApiManager
import com.example.banana_news.model.NewsResponse
import com.example.banana_news.model.SourceResponse
import com.example.banana_news.model.SourcesItem
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout:TabLayout
    lateinit var progressbar:ProgressBar
    lateinit var recycleview:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initview()
        getSources()


    }
    val adapter =NewsAdapter(null)
    fun initview(){
        tabLayout = findViewById(R.id.tabLayout)
        progressbar = findViewById(R.id.progress_bar)
        recycleview= findViewById(R.id.recycler_view)
        recycleview.adapter=adapter
    }



    private fun getSources(){
        progressbar.isVisible = true
        ApiManager.getApi().getSources(Constant.ApiKey)
            .enqueue(object : Callback<SourceResponse>{
                override fun onResponse(
                    call: Call<SourceResponse>,
                    response: Response<SourceResponse>
                ) {
                    progressbar.isVisible = false
                    addSourcesToTabLayout(response.body()?.sources)
                }

                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    progressbar.isVisible = false
                    Log.e("error" , t.localizedMessage)
                }


            })


    }

    private fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach {sources->
            val tab = tabLayout.newTab()
            tab.setText(sources?.name)
            tab.tag = sources
            tabLayout.addTab(tab)
            tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                   val  source = tab?.tag as SourcesItem
                    getNewsBySources(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val  source = tab?.tag as SourcesItem
                    getNewsBySources(source)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val  source = tab?.tag as SourcesItem
                    getNewsBySources(source)
                }


            })
        }

        tabLayout.getTabAt(0)?.select()
    }

    private fun getNewsBySources(source: SourcesItem) {
        progressbar.isVisible = true
        ApiManager.getApi().getNews(Constant.ApiKey , source.id?: "")
            .enqueue(object : Callback<NewsResponse>{
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressbar.isVisible = false
                    adapter.changeItem(response.body()?.articles)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressbar.isVisible = false
                    Log.e("error" , t.localizedMessage)
                }


            })
    }


}
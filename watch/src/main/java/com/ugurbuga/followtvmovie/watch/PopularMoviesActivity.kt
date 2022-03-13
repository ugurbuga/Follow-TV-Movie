package com.ugurbuga.followtvmovie.watch

import android.app.Activity
import android.os.Bundle
import androidx.wear.widget.WearableLinearLayoutManager
import com.ugurbuga.followtvmovie.watch.databinding.ActivityPopularMoviesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesActivity : Activity() {

    private lateinit var binding: ActivityPopularMoviesBinding

    lateinit var posterAdapter: PosterAdapter
    private lateinit var movieService: MovieService
    var page = 1
    var isCanLoadNewItem = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPopularMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieService = Network.provideRetrofit().create(MovieService::class.java)
        posterAdapter = PosterAdapter()

        with(binding.movieRecyclerView) {
            setHasFixedSize(true)
            isEdgeItemsCenteringEnabled = true
            layoutManager = WearableLinearLayoutManager(this@PopularMoviesActivity)
            scrollListener { visibleItemCount, firstVisibleItemPosition, totalItemCount ->
                if (Util.canPagingAvailable(
                        isCanLoadNewItem, visibleItemCount, firstVisibleItemPosition, totalItemCount
                    )
                ) {
                    isCanLoadNewItem = false
                    addLoadingAndGetNewItems()

                }
            }
            binding.movieRecyclerView.adapter = posterAdapter
        }

        posterAdapter.submitList(listOf(LoadingUIModel()))
        getItems()


    }

    private fun addLoadingAndGetNewItems() {
        var temp = posterAdapter.currentList.toMutableList()
        temp = temp.filter { it !is LoadingUIModel }.toMutableList()
        temp.add(LoadingUIModel())
        posterAdapter.submitList(temp)
        page++
        getItems()
    }

    private fun getItems() {
        val call: Call<MovieGeneralResponse> = movieService.getPopularMovies(page)

        call.enqueue(object : Callback<MovieGeneralResponse> {
            override fun onResponse(
                call: Call<MovieGeneralResponse>, response: Response<MovieGeneralResponse>
            ) {
                if (response.isSuccessful) {
                    var list = posterAdapter.currentList.toMutableList()
                    list = list.filter { it !is LoadingUIModel }.toMutableList()
                    list.addAll(response.body()!!.results)
                    posterAdapter.submitList(list.toMutableList())
                    isCanLoadNewItem = true
                }
            }

            override fun onFailure(call: Call<MovieGeneralResponse>, t: Throwable) {

            }
        })
    }
}
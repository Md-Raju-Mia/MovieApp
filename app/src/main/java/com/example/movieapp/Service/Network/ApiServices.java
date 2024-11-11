package com.example.movieapp.Service.Network;

import com.example.movieapp.Service.Model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("3/movie/top_rated?api_key=384f424fe2e27d292a322de20937f714")
    Call<MovieModel> getTopRatedMovieLists();
}

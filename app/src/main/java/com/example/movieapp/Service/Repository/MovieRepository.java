package com.example.movieapp.Service.Repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.Service.Model.MovieModel;
import com.example.movieapp.Service.Model.Result;
import com.example.movieapp.Service.Network.ApiServices;
import com.example.movieapp.Service.Network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static Context mcontext;
    private static MovieRepository instance;
    private MovieModel movieModel;
    private List<Result> mResult;
    private MutableLiveData mliveData;

    public static MovieRepository getInstance(Context context){
        if(instance==null){
            mcontext=context;
            instance = new MovieRepository();

        }
        return instance;
    }

    public MutableLiveData<List<Result>>  getTopRatedMovieLists(){
        if(mliveData ==null){
            mliveData = new MutableLiveData();
        }
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices.class);
        Call<MovieModel> call = apiServices.getTopRatedMovieLists();

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
              movieModel=response.body();
              mResult=movieModel.getResults();

              mliveData.postValue(mResult);

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
        return mliveData;
    }




}

package com.example.newsdrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsdrop.api.APIClient;
import com.example.newsdrop.models.Article;
import com.example.newsdrop.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment{
    List<Article> articleList=new ArrayList<Article>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rv= (RecyclerView) inflater.inflate(R.layout.activity_sports_fragment,container,false);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<News> call= APIClient.getClient().getCategorizedNews("in","sports",APIClient.API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful()){
                    if(response.code()==429){
                        Toast.makeText(inflater.getContext(),"Retry after sometime!",Toast.LENGTH_SHORT).show();
                    }else{Toast.makeText(inflater.getContext(),response.code()+"",Toast.LENGTH_SHORT).show();}

                    return;
                }
                News news = (News) response.body();
                articleList= news.getArticles();
                RVAdapter adapter = new RVAdapter(articleList,articleList.size());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(inflater.getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return rv;
    }
}

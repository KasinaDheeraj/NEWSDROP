package com.example.newsdrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsdrop.api.APIClient;
import com.example.newsdrop.models.Article;
import com.example.newsdrop.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.newsdrop.api.APIClient.getClient;

public class TopHeadlinesFragment extends Fragment {
    List<Article> aList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RVAdapter adapter = new RVAdapter(3);
        RecyclerView rv= (RecyclerView) inflater.inflate(R.layout.activity_top_headlines_fragment,container,false);
        rv.setAdapter(adapter);

        Call<News> call=APIClient.getClient().getHeadLines("in",APIClient.API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(inflater.getContext(),response.code()+"",Toast.LENGTH_SHORT).show();
                }
                News news = (News) response.body();
                List<Article> articleList=news.getArticles();
                for(Article a:articleList){
                    aList.add(a);

                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(inflater.getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return rv;
    }

}

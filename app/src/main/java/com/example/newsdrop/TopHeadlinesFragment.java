package com.example.newsdrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsdrop.api.APIClient;
import com.example.newsdrop.models.Article;
import com.example.newsdrop.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.newsdrop.api.APIClient.getClient;

public class TopHeadlinesFragment extends Fragment {
    List<Article> articleList=new ArrayList<Article>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Call<News> call=APIClient.getClient().getHeadLines("in",APIClient.API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful()){
                    Log.e("onNotSuccessful!!!",response.code()+"");
                    return;
                    //Toast.makeText(inflater.getContext(),response.code()+"",Toast.LENGTH_SHORT).show();
                }
                News news = (News) response.body();
                articleList= news.getArticles();



                //Toast.makeText(inflater.getContext(),"Success!!!",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
                //Toast.makeText(inflater.getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RVAdapter adapter = new RVAdapter(articleList,articleList.size());
        RecyclerView rv= (RecyclerView) inflater.inflate(R.layout.activity_top_headlines_fragment,container,false);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        return rv;
    }

}

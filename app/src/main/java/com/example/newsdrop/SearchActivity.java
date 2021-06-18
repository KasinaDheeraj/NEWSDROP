package com.example.newsdrop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

public class SearchActivity extends AppCompatActivity {

    public static String qUERY="";

    List<Article> articleList=new ArrayList<Article>();
    RecyclerView rv;
    Context context=this;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        result=findViewById(R.id.search_result);
        rv=(RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        String query= String.valueOf(getIntent().getExtras().get(qUERY));
        result.setText("Search results for "+query);
        setUpNetwork(query);
    }

    public void setUpNetwork(String s){
        Call<News> call= APIClient.getClient().getSearchNews(s,APIClient.API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful()){
                    if(response.code()==429){
                        Toast.makeText(context,"Retry after sometime!",Toast.LENGTH_SHORT).show();
                    }else{Toast.makeText(context,response.code()+"",Toast.LENGTH_SHORT).show();}

                    return;
                }
                News news = (News) response.body();
                articleList= news.getArticles();
                RVAdapter adapter = new RVAdapter(articleList,articleList.size());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

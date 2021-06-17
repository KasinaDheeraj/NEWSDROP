package com.example.newsdrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsdrop.api.APIClient;
import com.example.newsdrop.models.Article;
import com.example.newsdrop.models.News;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment{
    List<Article> articleList=new ArrayList<Article>();
    LayoutInflater inflater;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater=inflater;
        View r= inflater.inflate(R.layout.activity_search_fragment,container,false);
//        RecyclerView rv=(RecyclerView) r.findViewById(R.id.recyclerView);
//        RVAdapter adapter = new RVAdapter(articleList,articleList.size());
//        rv.setAdapter(adapter);
//        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        EditText editText=r.findViewById(R.id.editText);
        editText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction()==KeyEvent.ACTION_DOWN)&&(keyCode==KeyEvent.KEYCODE_ENTER)){
                    String query= editText.getText().toString();
                    editText.getText().clear();
                    Intent intent =new Intent(inflater.getContext(),SearchActivity.class);
                    intent.putExtra(SearchActivity.qUERY,query);
                    startActivity(intent);
//                    setUpNetwork(query);
//                    RVAdapter adapter = new RVAdapter(articleList,articleList.size());
//                    rv.setAdapter(adapter);
                    return true;
                }
                return false;
            }
        });

        return r;
    }
//    public void setUpNetwork(String s){
//        Call<News> call= APIClient.getClient().getSearchNews(s,APIClient.API_KEY);
//        call.enqueue(new Callback<News>() {
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                if(!response.isSuccessful()){
//                    if(response.code()==429){
//                        Toast.makeText(inflater.getContext(),"Retry after sometime!",Toast.LENGTH_SHORT).show();
//                    }else{Toast.makeText(inflater.getContext(),response.code()+"",Toast.LENGTH_SHORT).show();}
//
//                    return;
//                }
//                News news = (News) response.body();
//                articleList= news.getArticles();
//            }
//
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//                Toast.makeText(inflater.getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}

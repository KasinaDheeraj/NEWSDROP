package com.example.newsdrop;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsdrop.models.Article;

import java.util.ArrayList;
import java.util.List;

class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{
    List<Article> articleList;
    int count=0;

    ArrayList<String> titleList=new ArrayList<>();
    ArrayList<String> descriptionList=new ArrayList<>();
    ArrayList<String> dateList=new ArrayList<>();
    ArrayList<String> sourceList=new ArrayList<>();
    ArrayList<String> ImageurlList=new ArrayList<>();
    ArrayList<String> urlList=new ArrayList<>();

    public RVAdapter(List<Article> aList,int count){
        articleList=aList;
        this.count=count;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardview;
        public ViewHolder(CardView v){
            super(v);
            cardview=v;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cv =holder.cardview;

        setupLists(articleList);

        ImageView img=cv.findViewById(R.id.cv_image);
        if(ImageurlList.get(position)!=null){
        Glide.with(cv)
                .load(ImageurlList.get(position))
                .into(img);}else{
            img.setImageResource(R.mipmap.default_imageview);
        }

        TextView title=cv.findViewById(R.id.cv_title);
        title.setText(titleList.get(position));
        TextView desc=cv.findViewById(R.id.cv_description);
        desc.setText(descriptionList.get(position));
        TextView date=cv.findViewById(R.id.cv_date);
        date.setText(dateList.get(position));
        TextView source=cv.findViewById(R.id.source);
        source.setText(sourceList.get(position));



    }

    private void setupLists(List<Article> articleList) {
        for(int i=0;i<articleList.size();i++){
            titleList.add(articleList.get(i).getTitle());
            descriptionList.add(articleList.get(i).getDescription());
            dateList.add(articleList.get(i).getPublishedAt());
            sourceList.add((articleList.get(i).getSource()).getName());
            ImageurlList.add((articleList.get(i).getUrlToImage()));
            urlList.add(articleList.get(i).getUrl());

        }
    }

    @Override
    public int getItemCount() {
        return count;
    }
}

package com.example.newsdrop;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{
    int count=0;
    public RVAdapter(int count){this.count=count;}
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
        ImageView img=cv.findViewById(R.id.cv_image);
        TextView title=cv.findViewById(R.id.cv_title);
        TextView desc=cv.findViewById(R.id.cv_description);
        TextView date=cv.findViewById(R.id.cv_date);
        TextView source=cv.findViewById(R.id.source);



    }

    @Override
    public int getItemCount() {
        return count;
    }
}

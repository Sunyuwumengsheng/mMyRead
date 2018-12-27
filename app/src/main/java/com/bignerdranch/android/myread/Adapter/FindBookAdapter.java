package com.bignerdranch.android.myread;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FindBookAdapter extends RecyclerView.Adapter<FindBookAdapter.ViewHoder> {
    private ArrayList<String> arrayList;
    public FindBookAdapter(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }
    static class ViewHoder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView mTextView;
        private ImageView mImageView;
        public ViewHoder(View itemView) {
            super(itemView);
            mView = itemView;
            mImageView = (ImageView)itemView.findViewById(R.id.find_imageview);
            mTextView = (TextView)itemView.findViewById(R.id.find_text_view);
        }
    }
    private Context context;
    @Override
    public ViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_find_item,viewGroup,false);
        context = viewGroup.getContext();
        ViewHoder hoder = new ViewHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(ViewHoder viewHoder, int i) {
        viewHoder.mTextView.setText(arrayList.get(i));
        switch (i){
            case 0:
                Glide.with(context).load(R.drawable.qihuan).into(viewHoder.mImageView);
                break;
            case 1:
                Glide.with(context).load(R.drawable.ic_du_shi).into(viewHoder.mImageView);
                break;
            case 2:
                Glide.with(context).load(R.drawable.ic_xian_xia).into(viewHoder.mImageView);
                break;
            case 3:
                Glide.with(context).load(R.drawable.kk).into(viewHoder.mImageView);
                break;
            case 4:
                Glide.with(context).load(R.drawable.youxi).into(viewHoder.mImageView);
                break;
            case 5:
                Glide.with(context).load(R.drawable.lishil).into(viewHoder.mImageView);
                break;
            case 6:
                Glide.with(context).load(R.drawable.ic_xuan_huan).into(viewHoder.mImageView);
                break;
            case 7:
                Glide.with(context).load(R.drawable.xuanyi).into(viewHoder.mImageView);
                break;
            case 8:
                Glide.with(context).load(R.drawable.wuxia).into(viewHoder.mImageView);
                break;
            case 9:
                Glide.with(context).load(R.drawable.tongrenh).into(viewHoder.mImageView);
                break;
            case 10:
                Glide.with(context).load(R.drawable.zhic).into(viewHoder.mImageView);
                break;
            case 11:
                Glide.with(context).load(R.drawable.zhanzheng).into(viewHoder.mImageView);
                break;
            case 12:
                Glide.with(context).load(R.drawable.yanqinag).into(viewHoder.mImageView);
                break;
            case 13:
                Glide.with(context).load(R.drawable.tyu).into(viewHoder.mImageView);
                break;
            default:
                break;
        }


    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}

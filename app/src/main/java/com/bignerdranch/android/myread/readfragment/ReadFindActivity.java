package com.bignerdranch.android.myread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ReadFindActivity extends Fragment {
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private FindBookAdapter findBookAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("玄幻");
        arrayList.add("都市");
        arrayList.add("仙侠");
        arrayList.add("科幻");
        arrayList.add("游戏");
        arrayList.add("历史");
        arrayList.add("奇幻");
        arrayList.add("灵异");
        arrayList.add("武侠");
        arrayList.add("同人");
        arrayList.add("职场");
        arrayList.add("军事");
        arrayList.add("女生");
        arrayList.add("竞技");
        View view= inflater.inflate(R.layout.activity_find_read, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recylerview_find_book);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        findBookAdapter = new FindBookAdapter(arrayList);
        recyclerView.setAdapter(findBookAdapter);

        return view;
    }
}

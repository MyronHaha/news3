package com.example.whoami.news;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SetFragment extends Fragment {

    private GridView gridview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private View v;

    // 图片封装为一个数组
    private int[] icon = {
                            R.mipmap.chat,R.mipmap.chat,R.mipmap.chat,
                            R.mipmap.chat,R.mipmap.chat,R.mipmap.chat,
                            R.mipmap.chat,R.mipmap.chat
    };
    private String[] iconName = {
                                        "登录","注册","大厅","其他"
                                        ,"其他","其他","其他","其他"
    };
    private String[] iconDesc = {
            "快捷登录入口","注册用户发表观点","大厅...","其他..."
            ,"其他...","其他...","其他...","其他..."
    };

    public SetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("功能");
        this.v = view;
        this.gridview = (GridView) v.findViewById(R.id.grid);
        this.data_list = new ArrayList<Map<String, Object>>();
        getData();//初始化data_list
        String [] from ={"icon","iconName","iconDesc"};
        int[] to = {R.id.icon,R.id.iconName,R.id.iconDesc};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.griditem, from, to);
        gridview.setAdapter(sim_adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        login();//登录
                        break;
                    case 1:
                        register();
                        break;//注册
                    case 2:
                        room();
                }

            }
        });
    }

    private void room() {
        getActivity().startActivity(new Intent(getActivity(),AllUser.class));
    }

    public List<Map<String, Object>> getData(){
        for(int i= 0 ; i<icon.length ; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("icon",icon[i]);
            map.put("iconName",iconName[i]);
            map.put("iconDesc",iconDesc[i]);
            data_list.add(map);
        }
        return data_list;
    }

    public void login(){
        getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
    }
    public void register(){
        getActivity().startActivity(new Intent(getActivity(),RegisterActivity.class));
    }
}

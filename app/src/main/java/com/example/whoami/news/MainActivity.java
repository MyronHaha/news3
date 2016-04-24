package com.example.whoami.news;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.whoami.news.slideMenu.LocalNews;
import com.example.whoami.news.slideMenu.MyNews;
import com.example.whoami.news.slideMenu.SlideMenu;
import com.wilddog.client.Wilddog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Fragment> fralist;
    Intent menuIntent=new Intent();
    SlideMenu slideMenu;
    Bundle menu_bundle=new Bundle();//菜单bundle
    private FragmentManager fragmentManager = null;
    private FragmentTransaction transaction = null;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.slidemenu);

        super.onCreate(savedInstanceState);
        slideMenu = (SlideMenu) findViewById(R.id.slide);
        menu_bundle=new Bundle();

        init();
    }
    public void init(){
        Wilddog.setAndroidContext(this);

        ImageButton b=(ImageButton)findViewById(R.id.home);


        b.setOnClickListener(this);

        ImageButton b1=(ImageButton)findViewById(R.id.chat);
        b1.setOnClickListener(this);

        ImageButton b2 = (ImageButton)findViewById(R.id.set);
        b2.setOnClickListener(this);

        fralist = new ArrayList<>();
        NewsFragment newFrag = new NewsFragment();
        RoomFragment chatFrag = new RoomFragment();
        SetFragment setFrag = new SetFragment();
        fralist.add(newFrag);
        fralist.add(chatFrag);
        fralist.add(setFrag);


        fragmentManager = this.getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.body,newFrag);
        transaction.add(R.id.body,chatFrag);
        transaction.add(R.id.body, setFrag);
        transaction.show(newFrag).hide(chatFrag).hide(setFrag);
        transaction.commit();

    }
    //菜单触控
    public void localnew_click(View v){
        Toast.makeText(MainActivity.this, "localnews", Toast.LENGTH_SHORT).show();
        menuIntent=createIntent(MainActivity.this,LocalNews.class,"这是本地新闻啊");
        startActivity(menuIntent);

    }public void mynews_click(View v){
        Toast.makeText(MainActivity.this,"mynews",Toast.LENGTH_SHORT).show();
        menuIntent=createIntent(MainActivity.this,MyNews.class,"这是我的消息");
        startActivity(menuIntent);
    }
    public Intent createIntent(Context context, Class ca, String msg){
        Intent model=new Intent(context,ca);
        menu_bundle.putString("msg",msg);
        model.putExtra("menu_bundle",menu_bundle);
        return model;
    }
    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this, "run", Toast.LENGTH_SHORT).show();
            switch(view.getId()){
                case R.id.home:
                    switchFragment(0);
                    break;
                case R.id.chat:
                    switchFragment(1);
                    break;
                case R.id.set:
                    switchFragment(2);
                    break;
            }
    }

    public void switchFragment(int pos){
        transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        for (int i = 0; i < fralist.size(); i++) {
            if(pos==i){
                transaction.show(fralist.get(i));
            }else{
                transaction.hide(fralist.get(i));
            }
        }

        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if(fragmentManager.getBackStackEntryCount()==0){
                finish();
            }else{
                fragmentManager.popBackStack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


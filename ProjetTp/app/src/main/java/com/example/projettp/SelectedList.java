package com.example.projettp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SelectedList extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1, tab2, tab3;
    public PageAdapter pageAdapter;
    Toolbar toolbar;
TextView textinter;
Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_list3);
        tabLayout = findViewById(R.id.tablayout);
        tab1 = (TabItem) findViewById(R.id.tab1);
        tab2 = (TabItem) findViewById(R.id.tab2);
        tab3 = (TabItem) findViewById(R.id.tab3);
        viewPager = findViewById(R.id.view_pager);
        textinter=findViewById(R.id.textinter);
        toolbar=findViewById(R.id.myToolbar);
        sw=findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(),"terminé",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"non terminé",Toast.LENGTH_SHORT).show();
                }
            }
        });
setSupportActionBar(toolbar);
getSupportActionBar().setDisplayShowTitleEnabled(false);

pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
viewPager.setAdapter(pageAdapter);
tabLayout.setupWithViewPager(viewPager);
PagerAdapter pagerAdapter=new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
pageAdapter.addfragment(new tab1(),"Details");
pageAdapter.addfragment(new tab2(),"fichier");
pageAdapter.addfragment(new tab3(),"signature");
viewPager.setAdapter(pageAdapter);
        Intent in=getIntent();
       int internumber= in.getIntExtra("position",1);
textinter.setText("Intervention #"+internumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_select,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
            case R.id.refrech:
                Toast.makeText(this, "refrech", Toast.LENGTH_SHORT).show();
            case R.id.edit:
                Toast.makeText(this,"edit",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;

    }



        }





package com.example.projettp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

private final ArrayList<Fragment>fragmentArrayList=new ArrayList<>();
private final ArrayList<String>fragmentTitle=new ArrayList<>();
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addfragment(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle (int position){
        return fragmentTitle.get(position);
    }
}

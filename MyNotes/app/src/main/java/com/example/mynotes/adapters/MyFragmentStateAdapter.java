package com.example.mynotes.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mynotes.FragmentAdd;
import com.example.mynotes.FragmentDelete;
import com.example.mynotes.FragmentShow;
import com.example.mynotes.FragmentUpdate;
import com.example.mynotes.database.DB;
import com.example.mynotes.interfaces.DBUser;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    static final int PAGE_COUNT = 4;
    private DB db;

    public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, DB db) {
        super(fragmentActivity);
        this.db = db;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment frag = null;
        switch (position) {
            case 0: frag = new FragmentShow();break;
            case 1: frag = new FragmentAdd(); break;
            case 2: frag = new FragmentUpdate(); break;
            case 3: frag = new FragmentDelete(); break;
            default: break;
        }

        if (frag != null && frag instanceof DBUser) {
            ((DBUser) frag).setDatabase(db);
        }
        return frag;
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }

    public String getTabTitle(int position) {
        switch (position) {
            case 0: return "Show";
            case 1: return "Add";
            case 2: return "Update";
            case 3: return "Delete";
            default: return "";
        }
    }
}


package com.joesoft.globomantics;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

public class IdeaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_detail);


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(IdeaDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(IdeaDetailFragment.ARG_ITEM_ID, 0));
            IdeaDetailFragment fragment = new IdeaDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.idea_detail_container, fragment)
                    .commit();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.idea_detail_container, fragment, "Idea Detail");
//            // transaction.addToBackStack(getString(R.string.tag_fragment_profile));
//            transaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, IdeaListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

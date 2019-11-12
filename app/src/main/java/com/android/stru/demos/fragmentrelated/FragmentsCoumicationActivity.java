package com.android.stru.demos.fragmentrelated;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.stru.R;
import com.android.stru.demos.fragmentrelated.ui.main.SectionsPagerAdapter;
import com.android.stru.xfunctions.FunctionManager;
import com.android.stru.xfunctions.funcions.FuncNoParamNoResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class FragmentsCoumicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_coumication);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        FunctionManager.getInstance().addFunction(new FuncNoParamNoResult("fun1") {
            @Override
            public void function() {
                Toast.makeText(FragmentsCoumicationActivity.this, "fun1", Toast.LENGTH_SHORT).show();
            }
        }).addFunction(new FuncNoParamNoResult("fun2") {
            @Override
            public void function() {
                Toast.makeText(FragmentsCoumicationActivity.this, "fun2", Toast.LENGTH_SHORT).show();
            }
        }) ;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                onTestClickTAb();
            }
        });



    }

    @TimeConsumingTrace(value = "摇一摇",key = 1)
    private void onTestClickTAb() {

        Log.e("8888", "(((((((((((((((((((((9");
    }
}
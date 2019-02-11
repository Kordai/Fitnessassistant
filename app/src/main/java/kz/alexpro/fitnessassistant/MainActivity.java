/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kz.alexpro.fitnessassistant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.Gravity.*;

public class MainActivity extends AppCompatActivity {


    public int index;
    ListContentFragment frag1;
    TileContentFragment frag2;
    CardContentFragment frag3;
    TileContentFragment frag4;


    ArrayList<Double> ves;
    ArrayList<Double> rabota;
    ArrayList<Double> rabotaA;

    ViewPager viewPager;
    TabLayout tabs;

    Integer vesTela;
    Integer vozrast;
    Integer rost;
    Integer obshVremTren;
    Double rabotaFull;
    Double raznicaRost;
    Double raznicaVes;
    Double rashod;
    Double itog;
    long itogoKK;

    Context context = this;

    EditText vt, voz, ros, vr;


    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = 0;
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frag1 = new ListContentFragment();
        frag2 = new TileContentFragment();
        frag3 = new CardContentFragment();



        // Setting ViewPager for each Tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);



        frag2.uprojnenie = new ArrayList<>();
        frag2.vesInventar = new ArrayList<>();
        frag2.kolPodhodov = new ArrayList<>();
        frag2.indexVesTela = new ArrayList<>();
        frag2.indexPut = new ArrayList<>();


        frag3.uprojnenie = new ArrayList<>();
        frag3.min = new ArrayList<>();
        frag3.zatratyAerob = new ArrayList<>();





    }
    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(frag1, "Общая");
        adapter.addFragment(frag2, "Упражнения");
        adapter.addFragment(frag3, "Аэробные");
        viewPager.setAdapter(adapter);
    }



    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
public int getIndex(){
    return index;
}


    public void onClick(View view){

        ves = new ArrayList<>();
        rabota = new ArrayList<>();
        rabotaA = new ArrayList<>();
        rabotaFull = 0.0;

        vt = (EditText)findViewById(R.id.editText);
        ros = (EditText) findViewById(R.id.editText2);
        voz = (EditText) findViewById(R.id.editText3);
        vr = (EditText) findViewById(R.id.editText4);

        if (vt.getText().toString().equals("")||ros.getText().toString().equals("")||voz.getText().toString().equals("")||vr.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Заполните все поля!!!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(CENTER, 0, 0);
            toast.show();
        } else {

        vesTela = Integer.valueOf((vt).getText().toString());
        rost = Integer.valueOf((ros).getText().toString());
        vozrast = Integer.valueOf((voz).getText().toString());
        obshVremTren = Integer.valueOf((vr).getText().toString());




            //Вычисляем работу производимую во время тренировок
            for (int i = 0; i < frag2.indexVesTela.size(); i++) {
                ves.add(frag2.indexVesTela.get(i) * vesTela);
                rabota.add((frag2.kolPodhodov.get(i) * 19.6 * (ves.get(i) + frag2.vesInventar.get(i))) * frag2.indexPut.get(i));
            }

            //Вычисляем работу проводимую во время аэробных занятий
            for (int i = 0; i < frag3.zatratyAerob.size(); i++) {

                rabotaA.add(0.9 * (frag3.zatratyAerob.get(i) * vesTela * frag3.min.get(i)));
                Log.d(String.valueOf(frag3.zatratyAerob.get(i)), "Вес");
                Log.d(String.valueOf(vesTela), "Вес");
                Log.d(String.valueOf(frag3.min.get(i)), "Вес");


            }
            //Суммируем всю работу силовых занятий
            for (int i = 0; i < rabota.size(); i++) {

                rabotaFull = rabotaFull + rabota.get(i);
            }


            rabotaFull = (rabotaFull * 0.239) / 1000;

            rabotaFull = rabotaFull / frag1.opit;
//        //умножаем работу на опыт,вес, рост
//
            raznicaRost = rabotaFull / 100 * ((rost - 175) / 2);
            raznicaVes = rabotaFull / 100 * ((vesTela - 75));
            rabotaFull = rabotaFull + raznicaRost + raznicaVes;

            for (int i = 0; i < rabotaA.size(); i++) {

                rabotaFull = rabotaFull + rabotaA.get(i);
            }
            if (frag1.pl == 1) {
                rashod = frag1.aktivnost * (655 + (9.6 * vesTela) + (1.8 * rost) - (4.7 * vozrast));
            } else {
                rashod = frag1.aktivnost * (66 + (13.7 * vesTela) + (5 * rost) - (6.8 * vozrast));
            }

            itog = (rashod / 1440) * obshVremTren;
            Log.d(String.valueOf(rashod / 1440), "rashod");
            Log.d(String.valueOf(obshVremTren), "obsheevrenya");
            if (rabotaFull==0.0){itogoKK = 0;} else {
            itogoKK = Math.round(rabotaFull + itog);}


            Log.d(String.valueOf(itog), "itog");

            Log.d(String.valueOf(rabotaFull), "Работа");

            Log.d(String.valueOf(rashod), "Трата Ккал за сутки без учета тренировки!");

            Log.d(String.valueOf(itogoKK), "Трата Ккал за тренировку");

            Intent questionIntent = new Intent(MainActivity.this,
                    ResultActivity.class);
            questionIntent.putExtra("den",String.valueOf(Math.round(rashod)));
            questionIntent.putExtra("tren",String.valueOf(itogoKK));
            startActivity(questionIntent);

        }
//        Intent intent = new Intent(this, RsultActivity.class);
//        startActivity(intent);


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}


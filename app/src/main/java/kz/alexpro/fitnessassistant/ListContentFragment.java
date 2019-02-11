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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Provides UI for the view with List.
 */
public class ListContentFragment extends Fragment {

    String[] pol = {"Муж", "Жен"};
    String[] staj = {"0", "1-2", "3-4", "более 4"};
    String[] aktiv = {"Минимальный (сидячая работа)", "Средний (много хожу или езжу)", "Повышенный (в основном физический труд)", "Высокий (тяжелый физический труд)","Предельный (много тяжелой физической нагрузки)"};
    ArrayList<Double> indexPut1;

    Double opit;
    Double aktivnost;
    int pl;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.item_list, null);

        indexPut1 = new ArrayList<>();

        pl = 0;
        aktivnost = 1.2;
        opit = 0.2;
        // адаптер
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, pol);
        adapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, staj);
        adapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, aktiv);
        adapter3.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = (Spinner) v.findViewById(R.id.spinner);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) v.findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) v.findViewById(R.id.spinner5);
        spinner3.setAdapter(adapter3);

        // заголовок
        //spinner1.setPrompt("Title");
        //spinner2.setPrompt("Title");
        // выделяем элемент
        spinner1.setSelection(0);
        spinner2.setSelection(0);
        spinner3.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                switch (position){
                    case 0:
                        pl = 0;
                        break;
                    case 1:
                        pl = 1;
                        break;
                    default:
                        pl = 0;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                switch (position){
                    case 0:
                        opit = 0.2;
                        break;
                    case 1:
                        opit = 0.24;
                        break;
                    case 2:
                        opit = 0.27;
                        break;
                    case 3:
                        opit = 0.3;
                        break;
                    default:
                        opit = 0.2;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                switch (position){
                    case 0:
                        aktivnost = 1.2;
                        break;
                    case 1:
                        aktivnost = 1.38;
                        break;
                    case 2:
                        aktivnost = 1.56;
                        break;
                    case 3:
                        aktivnost = 1.73;
                        break;
                    case 4:
                        aktivnost = 1.95;
                        break;
                    default:
                        aktivnost = 1.2;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        return v;



    }


}

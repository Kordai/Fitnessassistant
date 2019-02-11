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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides UI for the view with Cards.
 */
public class CardContentFragment extends Fragment {

    String[] aerobnie = {"Выбирите аэробные упражнения","Прыжки на скакалке", "Велосипедный тренажер (средне)", "Велосипедный тренажер (быстро)", "Беговая дорожка / бег (медленно)","Беговая дорожка / бег (средне)",
    "Беговая дорожка / бег (быстро)","Элиптический тренажёр (медленно)","Элиптический тренажёр (средне)","Элиптический тренажёр (быстро)","Гребной тренажер"};

    Integer[] imageArray = {0,R.mipmap.skakalka, R.mipmap.velo, R.mipmap.velo, R.mipmap.beg, R.mipmap.beg, R.mipmap.beg, R.mipmap.trenajer,R.mipmap.trenajer, R.mipmap.trenajer, R.mipmap.greblja};

    Double[] zatraty = {0.0, 0.13, 0.123, 0.185, 0.1409, 0.1759, 0.255, 0.12, 0.16, 0.2, 0.123};

    Spinner spinner;

    String str;

    List<Uprojnenie> uprojnenie;
    ArrayList<Integer> min;
    ArrayList<Double> zatratyAerob;



    RecyclerView recyclerView;

    @Override
    public void onStart() {
        super.onStart();
        if (uprojnenie.size() > 0){
            initializeAdapter();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.item_card, null);




        spinner = (Spinner) v.findViewById(R.id.spinner4);
        final SpinnerAdapter adapter = new SpinnerAdapter(getActivity(), R.layout.spinner_layout, aerobnie,imageArray);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                // Получаем выбранный объект

                if (position==0){} else {

                    View promptsView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_uprojnenie, null);
                    final EditText vesInstr = (EditText) promptsView.findViewById(R.id.vesText1);
                    final EditText kolpod= (EditText) promptsView.findViewById(R.id.kolPodemovText);
                    TextView textView = (TextView) promptsView.findViewById(R.id.textView9);
                    TextView textView1 = (TextView) promptsView.findViewById(R.id.vvodVesText);

                    textView1.setText("Введите время трнировки (мин.)");
                    textView.setVisibility(View.INVISIBLE);
                    kolpod.setVisibility(View.INVISIBLE);
                    //Создаем AlertDialog
                    AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());

                    //Настраиваем prompt.xml для нашего AlertDialog:
                    mDialogBuilder.setView(promptsView);

                    //Настраиваем отображение поля для ввода текста в открытом диалоге:
                    //final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);


                    //Настраиваем сообщение в диалоговом окне:
                    mDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //Вводим текст и отображаем в строке ввода на основном экране:
                                            //  final_text.setText(userInput.getText());
                                            if (vesInstr.getText().toString().equals("")){
                                                str = "0 мин.(вы не указали время!)";
                                            } else {
                                                min.add(Integer.valueOf(vesInstr.getText().toString()));
                                                str = vesInstr.getText().toString() + " мин.";
                                                zatratyAerob.add(zatraty[position]);
                                            }
                                            uprojnenie.add(new Uprojnenie(aerobnie[position], str, imageArray[position]));

                                            initializeAdapter();

                                            spinner.setSelection(0);

                                        }
                                    })
                            .setNegativeButton("Отмена",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    //Создаем AlertDialog:
                    AlertDialog alertDialog = mDialogBuilder.create();

                    //и отображаем его:
                    alertDialog.show();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        recyclerView = (RecyclerView) v.findViewById(R.id.vie1);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        return v;


    }




    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(uprojnenie);
        recyclerView.setAdapter(adapter);
    }



}

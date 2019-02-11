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

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides UI for the view with Cards.
 */
public class TileContentFragment extends Fragment{

    Spinner spinner;
    Double[] vesTela = {0.0,0.75,0.75,0.00,0.75,0.00,0.00,0.75,0.75,0.00,0.00,0.40,0.00,0.00,0.75,0.00,0.20,0.40,0.00,0.00,0.00,0.75,0.75,0.75,0.50,0.00,0.00,1.00,1.00,0.60,0.60,1.00,
            0.00,0.00,0.00,0.00,0.00,0.20,0.00,0.20,0.05,0.40,0.40,0.6,0.6,0.0,0.0,0.5,0.1,0.1,0.5,0.0,0.0,0.6,0.4,0.1,0.0,0.0,0.0,0.1,0.1,0.1,0.05,0.05,0.05,0.0,0.0,1.0,0.05,0.05,0.1,0.6,
            0.5,0.02,0.0,0.0,0.0,0.0,0.75,0.75,0.75,0.75,0.75,0.75,0.3,0.0,0.3,0.3};
    Double[] put = {0.0,0.8,0.6,0.4,0.5,0.5,0.5,0.5,0.6,0.6,0.6,0.4,0.1,0.1,0.7,0.5,0.5,0.3,0.5,0.5,0.6,0.5,0.3,0.4,0.6,0.6,0.6,0.6,0.4,0.4,0.7,0.7,
            0.6,0.4,0.7,0.6,0.1,0.4,0.4,0.4,0.15,1.00,0.5,0.6,0.3,0.3,0.3,0.3,0.2,0.3,0.7,0.5,0.5,0.4,0.3,0.6,0.5,0.6,0.6,0.5,0.4,0.6,0.8,0.7,0.7,0.5,0.5,0.5,0.5,0.4,0.4,0.5,
            0.5,0.4,0.4,0.4,0.1,0.1,1.8,2.0,1.5,0.6,0.7,0.7,2.0,0.6,2.0,2.0};

    String[] textArray = {"Выбирите силовые упражнения","Приседания глубокие (любые)","Приседания неглубокие (любые)","Жим ногами в тренажёре","Выпады со штангой или гантелями","Сгибание ног в тренажёре",
            "Разгибание ног в тренажёре сидя","Зашагивания на лавку","Выпады в бок (перекаты)","Разведение ног в тренажёре","Сведение ног в тренажёре","Приседания в ГАКК-тренажёре",
            "Подъём на носки стоя (любой)","Подъём на носки сидя (любой)","Приседания на одной ноге","Разгибание бедра в тренажёре","Разгибание бедра лёжа на полу","Подъём таза лёжа",
            "Приведение ноги в кроссовере","Отведение ноги в кроссовере","Разгибание бедра в кроссовере","Становая тяга по полной амплитуде","Становая тяга по неполной амплитуде",
            "Становая тяга сумо","Наклоны со штангой на плечах","Тяга горизонтального блока","Рычажная тяга в тренажёре","Подтягивания узким и средним хватом","Подтягивания широким хватом",
            "Подтягивания в машине Смита","Гиперэкстензия","Гиперэкстензия в тренажёре","Тяга вертикального блока узким и средним хв.","Тяга вертикального блока широким хватом",
            "Пуловер лёжа","Пуловер в тренажёре","Трапеции (шраги) стоя","Тяга штанги/гантелей в наклоне","Тяга Т-образного грифа лёжа","Тяга Т-образного грифа в наклоне","Сгибания/разгибание шеи лёжа",
            "Подъём прямых ног (любой)","Подъём согнутых ног (любой)","Скручивания (полная амплитуда)","Скручивания (частичная амплитуда)","Скручивания с верхнего блока","Скручивания на тренажёре",
            "Боковые наклоны через козла","Вращение корпуса с палочкой","Наклоны в бок с гантелей стоя","Складка (любая)","Жим лёжа горизонтально (любой)","Жим лёжа под углом (любой)",
            "Отжимания от пола широким хватом","Отжимания от лавки широким хватом","Разводы с гантелями лёжа","Жим в грудном тренажёре сидя","Сведение рук в тренажёре","Сведение рук в кроссовере (любое)",
            "Жим стоя или сидя средним хватом","Жим стоя или сидя широким хватом","Протяжка до подбородка","Махи  вперёд (любые)","Махи в стороны стоя (любые)","Махи в стороны в наклоне (любые)",
            "Жим вверх в тренажёре сидя","Разведение рук назад в тренажёре","Отжимания от брусьев","Жим штанги узким хватом","Французский жим (любой)","Разгибание рук с верхнего блока",
            "Отжимания от пола узким хватом","Отжимания от лавки сзади","Разгибание рук с гантелями в наклоне","Сгибание рук стоя (любое)","Сгибание рук сидя (любое)","Сгибание запястий (любое)",
            "Разгибание запястий (любое)","Рывок штанги классический","Толчок штанги классический","Забрасывание штанги на грудь в сед","Толчок со стоек и швунг","Тяга рывковая",
            "Тяга толчковая","Рывок гири","Толчок гирь","Толчок гирь (длинный цикл)","Махи гирей над головой"};
    Integer[] imageArray = {0,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,
            R.mipmap.ikry,R.mipmap.ikry,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,R.mipmap.nogy,
            R.mipmap.spina,R.mipmap.spina,R.mipmap.spina,R.mipmap.spina,R.mipmap.spina,R.mipmap.spina,R.mipmap.podtyagivani,R.mipmap.podtyagivani,R.mipmap.podtyagivani,
            R.mipmap.spina,R.mipmap.spina,R.mipmap.podtyagivani,R.mipmap.podtyagivani,R.mipmap.spina,R.mipmap.spina,R.mipmap.spina,R.mipmap.spina,R.mipmap.spina,R.mipmap.spina,
            R.mipmap.spina,R.mipmap.press,R.mipmap.press,R.mipmap.press,R.mipmap.press,R.mipmap.press,R.mipmap.press,R.mipmap.press,R.mipmap.press,R.mipmap.press,R.mipmap.press,
            R.mipmap.grud,R.mipmap.grud,R.mipmap.grud,R.mipmap.grud,R.mipmap.grud,R.mipmap.grud,R.mipmap.grud,R.mipmap.grud,
            R.mipmap.pleshi,R.mipmap.pleshi,R.mipmap.pleshi,R.mipmap.pleshi,R.mipmap.pleshi,R.mipmap.pleshi,R.mipmap.pleshi,R.mipmap.pleshi,
            R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,R.mipmap.ruki,
            R.mipmap.tolchek_shtanga,R.mipmap.tolchek_shtanga,R.mipmap.tolchek_shtanga,R.mipmap.tolchek_shtanga,R.mipmap.tolchek_shtanga,R.mipmap.tolchek_shtanga,R.mipmap.girya,
            R.mipmap.girya,R.mipmap.girya,R.mipmap.girya};

    List<Uprojnenie> uprojnenie;
    RecyclerView recyclerView;
    EditText textVes;
    String textVesData;
    EditText textPodey;
    String textPodieyData;
    ArrayList<Integer> vesInventar;
    ArrayList<Integer> kolPodhodov;
    ArrayList<Double> indexVesTela;
    ArrayList<Double> indexPut;

    String str;

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

        View v = inflater.inflate(R.layout.item_tile, null);




        spinner = (Spinner) v.findViewById(R.id.spinner3);
        SpinnerAdapter adapter = new SpinnerAdapter(getActivity(), R.layout.spinner_layout, textArray,imageArray);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                // Получаем выбранный объект

                if (position==0){} else {

                    View promptsView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_uprojnenie, null);
                    final EditText vesInstr = (EditText) promptsView.findViewById(R.id.vesText1);
                    final EditText kolpod= (EditText) promptsView.findViewById(R.id.kolPodemovText);
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
                                                str = "Вес 0 кг. ";
                                                vesInstr.setText("0");
                                                if (kolpod.getText().toString().equals("")){
                                                    str = str + "выполнять 0 раз.(результата нет)";
                                                    kolpod.setText("0");
                                                } else {
                                                    str = str + ", выполнять " + kolpod.getText().toString() + " раз.";
                                                    vesInventar.add(Integer.valueOf(vesInstr.getText().toString()));
                                                    kolPodhodov.add(Integer.valueOf(kolpod.getText().toString()));
                                                    indexVesTela.add(vesTela[position]);
                                                    indexPut.add(put[position]);
                                                        }

                                            } else {
                                                if (kolpod.getText().toString().equals("")){
                                                    str = "Вес " + vesInstr.getText().toString() + ", выполнять 0 раз.(резултата нет)";
                                                    kolpod.setText("0");
                                                } else {
                                                    str = "Вес " + vesInstr.getText().toString() + ", выполнять " + kolpod.getText().toString() + " раз.";
                                                }
                                                    vesInventar.add(Integer.valueOf(vesInstr.getText().toString()));
                                                    kolPodhodov.add(Integer.valueOf(kolpod.getText().toString()));
                                                    indexVesTela.add(vesTela[position]);
                                                    indexPut.add(put[position]);
                                                }
                                            uprojnenie.add(new Uprojnenie(textArray[position], str, imageArray[position]));
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

        recyclerView = (RecyclerView) v.findViewById(R.id.vie);

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


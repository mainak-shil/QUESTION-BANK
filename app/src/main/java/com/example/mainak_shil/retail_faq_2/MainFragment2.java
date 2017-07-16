package com.example.mainak_shil.retail_faq_2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment2 extends Fragment implements View.OnClickListener {

    public static int no_ques = 20;
    public static String ques_arr[] = new String[no_ques];
    public static String ans_arr[][] = new String[no_ques][4];
    public static int correct_ans[] = new int[no_ques];
    public static boolean res[] = new boolean[no_ques];


    public int i;
    public int correct;
    public TextView questno, question ;
    public RadioButton ch1, ch2, ch3, ch4;
    public RadioGroup rg;
    public Button submit, clear;



    public BufferedReader reader;

    public String sub_name;


    public MainFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submit = (Button) getActivity().findViewById(R.id.button);
        clear = (Button) getActivity().findViewById(R.id.button2);
        questno = (TextView) getActivity().findViewById(R.id.textView3);
        question = (TextView) getActivity().findViewById(R.id.textView4);
        rg = (RadioGroup) getActivity().findViewById(R.id.radioGroup);
        ch1 = (RadioButton) getActivity().findViewById(R.id.radioButton);
        ch2 = (RadioButton) getActivity().findViewById(R.id.radioButton2);
        ch3 = (RadioButton) getActivity().findViewById(R.id.radioButton3);
        ch4 = (RadioButton) getActivity().findViewById(R.id.radioButton4);

        int i = 0 ;

        InputStream iS = null;
        try {
            iS = getResources().getAssets().open("questions_file2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(new InputStreamReader(iS));
    }

    @Override
    public void onStart() {
        super.onStart();

        if (i < no_ques) {
            rg.clearCheck();


            try {
                ques_arr[i] = reader.readLine();
                questno.setText("Question " + (i + 1));
                question.setText(ques_arr[i]);
                ans_arr[i][0] = reader.readLine();
                ans_arr[i][1] = reader.readLine();
                ans_arr[i][2] = reader.readLine();
                ans_arr[i][3] = reader.readLine();
                ch1.setText(ans_arr[i][0]);
                ch2.setText(ans_arr[i][1]);
                ch3.setText(ans_arr[i][2]);
                ch4.setText(ans_arr[i][3]);
                correct = Integer.parseInt(reader.readLine());
                correct_ans[i] = correct;

            } catch (IOException e) {
                e.printStackTrace();
            }


            clear.setOnClickListener(this);
            submit.setOnClickListener(this);
        } else {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_container, new ResultFragment2(), "resultFrag");
            ft.commit();

        }
    }

        @Override
        public void onClick (View v){
            switch (v.getId())
            {
                case R.id.button:
                    rg.clearCheck();
                    break;
                case R.id.button2:
                    if(rg.getCheckedRadioButtonId()==-1)
                    {
                        Toast.makeText(getContext(),"Please make a valid selection...",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else if(rg.getCheckedRadioButtonId()==R.id.radioButton) {
                        if (correct_ans[i] == 1)
                            res[i] = true;
                        else
                            res[i] = false;

                        i+=1;
                    }
                    else if(rg.getCheckedRadioButtonId()==R.id.radioButton2)
                    {
                        if(correct_ans[i] == 2)
                            res[i] = true;
                        else
                            res[i]= false;

                        i+=1;
                    }
                    else if(rg.getCheckedRadioButtonId()==R.id.radioButton3) {
                        if (correct_ans[i] == 3)
                            res[i] = true;
                        else
                            res[i] = false;


                        i+=1;
                    }
                    else if(rg.getCheckedRadioButtonId()==R.id.radioButton4) {
                        if (correct_ans[i] == 4)
                            res[i] = true;
                        else
                            res[i] = false;


                        i+=1;
                    }

                    onStart();

            }
        }




    }




package com.example.mainak_shil.retail_faq_2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {


    public TextView tvTot, tvCorr;
    public ListView lvRes ;
    public Button homeButton;

    public ArrayAdapter<String> adapter;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvTot = (TextView) getView().findViewById(R.id.textView8);
        tvCorr = (TextView) getView().findViewById(R.id.textView9);
        lvRes = (ListView) getView().findViewById(R.id.listView);
        homeButton = (Button) getView().findViewById(R.id.button3);
    }

    @Override
    public void onStart() {
        super.onStart();

        final String correctArray[]= new String[MainFragment.no_ques];
        String ques_array[] = new String[MainFragment.no_ques];

        lvRes.smoothScrollByOffset(10);

        tvTot.setText("Total: "+MainFragment.no_ques);
        int corr=0;
        for (int i = 0 ; i < MainFragment.no_ques; i++)
        {
            if (MainFragment.res[i])
            {
                corr++;
            }
        }

        tvCorr.setText("Correct: "+corr);

        for (int i = 0 ; i < MainFragment.no_ques; i++)
        {

            int correctOption = MainFragment.correct_ans[i]-1;
            correctArray[i] = MainFragment.ans_arr[i][correctOption];

        }
        for (int i = 0 ; i < MainFragment.no_ques;i++)
        {
            String flag = "";
            if(MainFragment.res[i])
                flag = "    (Correct)";
            else
                flag= "      (Wrong)";
            ques_array[i] = MainFragment.ques_arr[i] + flag;
        }
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,ques_array);
        lvRes.setAdapter(adapter);

        lvRes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Correct answer : "+correctArray[position], Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_container, new MainContent(), "mainFrag");
                ft.commit();
            }
        });
    }

}


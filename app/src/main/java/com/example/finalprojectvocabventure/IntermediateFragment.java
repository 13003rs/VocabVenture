package com.example.finalprojectvocabventure;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class IntermediateFragment extends Fragment {
    Button readWrite,spelling,grammar,exit;
    public IntermediateFragment() { // declaring a public constructor to call it out sa homeactivity class
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int gradeLevel = getArguments().getInt("UserGradeLevel", 0); // Retrieve grade level passed from HomeActivity

        View view = inflater.inflate(R.layout.intermediate, container, false);

        //-- initializing variables
        readWrite=view.findViewById(R.id.btnIntermediateReadWrite);
        spelling=view.findViewById(R.id.btnIntermediateSpelling);
        grammar=view.findViewById(R.id.btnIntermediateGrammar);
        exit=view.findViewById(R.id.btnInterExit);

        //-- exit button function
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeAct=new Intent(getContext(), HomeActivity.class);
                startActivity(homeAct);
            }
        });

        readWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checking and get the passed grade level value
                Log.d("UserGradeLevel", "Value: " + gradeLevel);
                Intent intent;

                //this handle the reading section basic
                if (gradeLevel == 3) {
                    intent = new Intent(requireContext(), g3InterRead1.class);
                } else if (gradeLevel == 4) {
                    intent = new Intent(requireContext(), g4InterRead1.class);
                } else if (gradeLevel == 5) {
                    intent = new Intent(requireContext(), g5InterRead1.class);
                } else {
                    return;
                }

                startActivity(intent);
            }
        });
        spelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checking and get the passed grade level value
                Log.d("UserGradeLevel", "Value: " + gradeLevel);
                Intent intent;

                //this handle the grammar section basic
                if (gradeLevel == 3) {
                    intent = new Intent(requireContext(), g3InterSpelling1.class);
                } else if (gradeLevel == 4) {
                    intent = new Intent(requireContext(), g4InterSpelling1.class);
                } else if (gradeLevel == 5) {
                    intent = new Intent(requireContext(), g5InterSpelling1.class);
                } else {
                    return;
                }

                startActivity(intent);
            }
        });
        grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checking and get the passed grade level value
                Log.d("UserGradeLevel", "Value: " + gradeLevel);
                Intent intent;

                //this handle the spelling section basic
                if (gradeLevel == 3) {
                    intent = new Intent(requireContext(), g3InterGrammar1.class);
                } else if (gradeLevel == 4){
                  intent = new Intent(requireContext(), g4InterGrammar1.class);
                }
                else if (gradeLevel == 5){
                    intent = new Intent(requireContext(), g5InterGrammar1.class);
                } else {
                    return;
                }

                startActivity(intent);
            }
        });

        return view;
    }


}
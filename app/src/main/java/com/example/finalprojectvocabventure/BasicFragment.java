package com.example.finalprojectvocabventure;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class BasicFragment extends Fragment {
    Button readWrite, spelling, grammar, exit;

    public BasicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int gradeLevel = getArguments().getInt("UserGradeLevel", 0); // Retrieve grade level passed from HomeActivity

        View view = inflater.inflate(R.layout.basic, container, false);

        readWrite = view.findViewById(R.id.btnBasicReadWrite);
        spelling = view.findViewById(R.id.btnBasicSpelling);
        grammar = view.findViewById(R.id.btnBasicGrammar);
        exit = view.findViewById(R.id.btnBasicExit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeAct = new Intent(getActivity(), HomeActivity.class);
                startActivity(homeAct);
                getActivity().finish();
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
                    intent = new Intent(requireContext(), g3BasicRead1.class);
                } else if (gradeLevel == 4) {
                    intent = new Intent(requireContext(), g4BasicRead1.class);
                } else if (gradeLevel == 5) {
                    intent = new Intent(requireContext(), g5BasicRead1.class);
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
                    intent = new Intent(requireContext(), g3BasicSpelling1.class);
                } else if (gradeLevel == 4) {
                    intent = new Intent(requireContext(), g4BasicSpelling1.class);
                } else if (gradeLevel == 5) {
                    intent = new Intent(requireContext(), g5BasicSpelling1.class);
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

                //this handle the grammar section basic
                if (gradeLevel == 3) {
                    intent = new Intent(requireContext(), g3BasicGrammar1.class);
                } else if (gradeLevel == 4) {
                    intent = new Intent(requireContext(), g4BasicGrammar1.class);
                } else if (gradeLevel == 5) {
                    intent = new Intent(requireContext(), g5BasicGrammar1.class);
                } else {
                    return;
                }

                startActivity(intent);
            }
        });

        return view;
    }
}



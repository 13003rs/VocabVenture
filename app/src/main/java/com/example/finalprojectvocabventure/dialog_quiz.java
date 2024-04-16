package com.example.finalprojectvocabventure;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class dialog_quiz extends AppCompatDialogFragment {

    private Class<?> targetClass;


    // Constructor to accept the target class
    public dialog_quiz(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_quiz, null);

        setupDialog(builder, view);
        AlertDialog dialog = builder.create();

        setButtonColors(dialog);
        adjustDialogSize(dialog);


        return dialog;
    }

    private void setupDialog(AlertDialog.Builder builder, View view) {
        builder.setView(view)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle OK click - Start the next activity based on the targetClass
                        if (targetClass != null) {
                            Intent intent = new Intent(getActivity(), targetClass);
                            getActivity().startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("Go back home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Replace MainActivity.class with the appropriate class you want to start
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
    }

    private void setButtonColors(AlertDialog dialog) {
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(0xFF000000);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(0xFF000000);
            }
        });
    }

    private void adjustDialogSize(AlertDialog dialog) {
        if (dialog != null) {
            dialog.getWindow().setLayout(1000, 1000);
        }
    }


}
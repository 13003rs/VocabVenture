package com.example.finalprojectvocabventure;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class dialog_correct extends AppCompatDialogFragment {

    private Class<?> targetClass;


    // Constructor to accept the target class
    public dialog_correct(Class<?> targetClass) {
        this.targetClass = targetClass;
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create an AlertDialog.Builder instance
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate the layout for the dialog
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_correct, null);

        // Create and start the MediaPlayer for the correct sound
        MediaPlayer correctSound = MediaPlayer.create(getActivity(), R.raw.correct_sound);
        correctSound.start();

        // Set up the dialog content and buttons
        setupDialog(builder, view);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();

        // Set text color for both positive and negative buttons if needed
        setButtonColors(dialog);

        // Adjust the dialog window size if needed
        adjustDialogSize(dialog);


        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Adjust the dialog window size if needed
        adjustDialogSize((AlertDialog) getDialog());
    }

    private void setupDialog(AlertDialog.Builder builder, View view) {
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle OK click - Start the next activity based on the targetClass
                        if (targetClass != null) {
                            Intent intent = new Intent(getActivity(), targetClass);
                            getActivity().startActivity(intent);
                        }
                    }
                });
    }



    private void setButtonColors(AlertDialog dialog) {
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                // Set text color for the positive button if needed
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(0xFF000000);

            }
        });
    }

    private void adjustDialogSize(AlertDialog dialog) {
        if (dialog != null) {
            dialog.getWindow().setLayout(1000, 1000); // size in pixels
        }
    }
}
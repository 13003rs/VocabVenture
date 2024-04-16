package com.example.finalprojectvocabventure;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class dialog_wrong extends AppCompatDialogFragment {

    private Class<?> targetClass;

    // Constructor to accept the target class
    public dialog_wrong(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_wrong, null);

        MediaPlayer wrongSound = MediaPlayer.create(getActivity(), R.raw.wrong_sound);
        wrongSound.start();

        setupDialog(builder, view);
        AlertDialog dialog = builder.create();

        setButtonColors(dialog);
        adjustDialogSize(dialog);

        return dialog;
    }

    private void setupDialog(AlertDialog.Builder builder, View view) {
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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

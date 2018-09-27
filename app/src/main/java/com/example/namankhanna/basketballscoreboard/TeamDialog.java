package com.example.namankhanna.basketballscoreboard;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class TeamDialog extends AppCompatDialogFragment {

    EditText etTeam1, etTeam2;

    public interface OnPositiveButtonListener {
        void getTeamNames(String team1, String team2);
    }

    OnPositiveButtonListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Create the builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Create the inflater object
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Inflate the dialog layout in a view object
        View view = inflater.inflate(R.layout.dialog_team, null);

        //Store the references of views
        etTeam1 = view.findViewById(R.id.etTeam1);
        etTeam2 = view.findViewById(R.id.etTeam2);

        builder.setView(view)
                .setTitle("Team Names")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.getTeamNames(
                                etTeam1.getText().toString(),
                                etTeam2.getText().toString()
                        );
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnPositiveButtonListener) context;
        }
        catch (ClassCastException cce) {
            throw new ClassCastException(context.toString() + "Interface must be implemented");
        }
    }
}

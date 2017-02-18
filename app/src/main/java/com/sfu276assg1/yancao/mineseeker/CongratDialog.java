package com.sfu276assg1.yancao.mineseeker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by song on 2017-02-17.
 */

public class CongratDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.gameover_layout,null);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==DialogInterface.BUTTON_POSITIVE)
                    getActivity().finish();
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("YOU WON!").setView(v)
                .setPositiveButton(android.R.string.ok,listener).create();

    }
}

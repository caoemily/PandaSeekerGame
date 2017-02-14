package com.sfu276assg1.yancao.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {

    public static final String BOARD_INFO_PREF = "boardInfoPref";
    public static final String NUM_INFO_PREF = "numInfoPref";
    public static final String BOARD_ROW_CHOSEN = "board num chosen";
    public static final String PANDA_NUM_CHOSEN = "panda num chosen";
    public static final String BOARD_COL_CHOSEN = "boardcolchosen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        setBoardSize();
        setNumPanda();
        setAcceptButton();
    }

    private void setAcceptButton() {
        Button btn = (Button)findViewById(R.id.btn_AcceptOptions);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rowtemp = getRowInfo(getApplicationContext());
                int coltemp = getColInfo(getApplicationContext());
                int numtemp = getNumInfo(getApplicationContext());
                Toast.makeText(OptionActivity.this, "To play: "+ rowtemp +" * "+ coltemp +
                        " board with "+numtemp+" pandas.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void setBoardSize() {
        RadioGroup boardGroup = (RadioGroup) findViewById(R.id.radioGroup_boardSize);
        int[] rowNum = getResources().getIntArray(R.array.rows);
        int[] colNum = getResources().getIntArray(R.array.cols);
        for(int i=0; i<rowNum.length; i++){
            final int row = rowNum[i];
            final int col = colNum[i];
            RadioButton btn_boardOption = new RadioButton(this);
            btn_boardOption.setText(row +" * "+col);
            btn_boardOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this, "You choose size: "+row+"*"+col,
                            Toast.LENGTH_SHORT).show();
                    saveBoardInfo(row,col);
                }
            });
            boardGroup.addView(btn_boardOption);

            //Select default button from sharedPref:
            if(row== getRowInfo(this)){
                btn_boardOption.setChecked(true);
            }
        }
    }

    private void setNumPanda() {
        RadioGroup numGroup = (RadioGroup) findViewById(R.id.radioGroup_num);
        int[] nums = getResources().getIntArray(R.array.numPanda);
        for(int i=0; i<nums.length; i++){
            final int  num= nums[i];
            RadioButton btn_numOption = new RadioButton(this);
            btn_numOption.setText(num+"");
            btn_numOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this, "You choose "+num+" pandas on the board.",
                            Toast.LENGTH_SHORT).show();
                    saveNumInfo(num);
                }
            });
            numGroup.addView(btn_numOption);
            if(num==getNumInfo(this)){
                btn_numOption.setChecked(true);
            }
        }
    }

    private void saveBoardInfo(int rown,int coln){
        SharedPreferences prefs = this.getSharedPreferences(BOARD_INFO_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(BOARD_ROW_CHOSEN,rown);
        editor.putInt(BOARD_COL_CHOSEN,coln);
        editor.apply();
    }

    private void saveNumInfo(int numn){
        SharedPreferences prefs = this.getSharedPreferences(NUM_INFO_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PANDA_NUM_CHOSEN,numn);
        editor.apply();
    }

    static public int getRowInfo(Context context){
        SharedPreferences prefs = context.getSharedPreferences(BOARD_INFO_PREF,MODE_PRIVATE);
        int defaultRow = context.getResources().getInteger(R.integer.default_row);
        return prefs.getInt(BOARD_ROW_CHOSEN,defaultRow);
    }

    static public int getColInfo(Context context){
        SharedPreferences prefs = context.getSharedPreferences(BOARD_INFO_PREF,MODE_PRIVATE);
        int defaultCol = context.getResources().getInteger(R.integer.default_col);
        return prefs.getInt(BOARD_COL_CHOSEN,defaultCol);
    }

    static public int getNumInfo(Context context){
        SharedPreferences prefs = context.getSharedPreferences(NUM_INFO_PREF,MODE_PRIVATE);
        int defaultNum = context.getResources().getInteger(R.integer.default_num);
        return prefs.getInt(PANDA_NUM_CHOSEN,defaultNum);
    }
}


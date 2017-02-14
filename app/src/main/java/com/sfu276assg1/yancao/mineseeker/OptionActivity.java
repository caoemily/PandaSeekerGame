package com.sfu276assg1.yancao.mineseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        creatBoardSize();
        creatNumPanda();
        setAcceptButton();
    }

    private void setAcceptButton() {
        Button btn = (Button) findViewById(R.id.btn_AcceptOptions);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup boardGroup = (RadioGroup) findViewById(R.id.radioGroup_boardSize);
                int idOfBoards = boardGroup.getCheckedRadioButtonId();
                RadioButton radioBoard = (RadioButton) findViewById(idOfBoards);
                String boardMessage = radioBoard.getText().toString();

                RadioGroup numGroup = (RadioGroup) findViewById(R.id.radioGroup_num);
                int idOfNums = boardGroup.getCheckedRadioButtonId();
                RadioButton numBoard = (RadioButton) findViewById(idOfNums);
                String numMessage = numBoard.getText().toString();
                Toast.makeText(OptionActivity.this, "To play: "+
                boardMessage+" board with "+numMessage+" pandas.", Toast.LENGTH_SHORT).show();
                Intent optionSettingIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(optionSettingIntent);

            }
        });
    }

    private void creatBoardSize() {
        RadioGroup boardGroup = (RadioGroup) findViewById(R.id.radioGroup_boardSize);
        int[] rowNum = getResources().getIntArray(R.array.rows);
        int[] colNum = getResources().getIntArray(R.array.cols);
        for (int i = 0; i < rowNum.length; i++) {
            final int row = rowNum[i];
            final int col = colNum[i];
            RadioButton btn_boardOption = new RadioButton(this);
            btn_boardOption.setText(row + " * " + col);
            btn_boardOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this, "You choose size: " + row + "*" + col,
                            Toast.LENGTH_SHORT).show();
                }
            });
            boardGroup.addView(btn_boardOption);
        }
    }

    private void creatNumPanda() {
        RadioGroup numGroup = (RadioGroup) findViewById(R.id.radioGroup_num);
        int[] nums = getResources().getIntArray(R.array.numPanda);
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];
            RadioButton btn_numOption = new RadioButton(this);
            btn_numOption.setText(num + "");
            btn_numOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this, "You choose " + num + " pandas on the board.",
                            Toast.LENGTH_SHORT).show();
                }
            });
            numGroup.addView(btn_numOption);
        }
    }

}

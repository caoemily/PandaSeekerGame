package com.sfu276assg1.yancao.mineseeker;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.button;
import static com.sfu276assg1.yancao.mineseeker.OptionActivity.getColInfo;
import static com.sfu276assg1.yancao.mineseeker.OptionActivity.getNumInfo;
import static com.sfu276assg1.yancao.mineseeker.OptionActivity.getRowInfo;

public class MainActivity extends AppCompatActivity {

    private int NUM_ROWS;
    private int NUM_COLS;
    private int NUM_PANDAS;
    private int numScan=0;
    private int numFound=0;

    private Table gameGrid;
    Button buttons[][];
    TextView scanUsed;
    TextView foundPanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeGame();
        populateButtons();
    }

    private void initializeGame() {
        NUM_ROWS = OptionActivity.getRowInfo(this);
        NUM_COLS = OptionActivity.getColInfo(this);
        NUM_PANDAS = OptionActivity.getNumInfo(this);

        buttons = new Button[NUM_ROWS][NUM_COLS];
        gameGrid = new Table(NUM_ROWS,NUM_COLS,NUM_PANDAS);

        foundPanda = (TextView) findViewById(R.id.numFoundText);
        foundPanda.setText("Found "+ numFound+" of " + NUM_PANDAS + " Pandas");

        scanUsed = (TextView) findViewById(R.id.numScanText);
        scanUsed.setText("# Scans used: " + numScan);
    }

    private void populateButtons() {

        TableLayout table = (TableLayout) findViewById(R.id.gameTable);
        for (int row =0; row<NUM_ROWS;row++){
            TableRow tableRow = new TableRow(this);
            table.addView(tableRow);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,1.0f
            ));

            for (int col=0;col<NUM_COLS;col++){
                final int FINAL_ROW =row;
                final int FINAL_COL =col ;
                Button button = new Button(this);
                tableRow.addView(button);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f
                ));
                button.setPadding(0,0,0,0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        Button button = buttons[row][col];
        lockButtonSizes();

        if (gameGrid.isReveal(row,col)&&gameGrid.isPanda(row,col)){
            if (!gameGrid.isNumReveal(row,col)){
                setRowAnimation(row,col);
                gameGrid.setNumReveal(row,col);
                int count = gameGrid.getCountPanda(row,col);
                button.setText(""+count);
                numScan++;
                scanUsed.setText("# Scans used: " + numScan);
            }
        }
        else if (!gameGrid.isReveal(row,col)&&gameGrid.isPanda(row,col)){
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pandasmall);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            gameGrid.reveal(row,col);
            String temp ="";
            int currentCount=0;
            for (int i=0;i<NUM_ROWS;i++){
                if (gameGrid.isNumReveal(i,col)){
                    temp = (String) buttons[i][col].getText();
                    currentCount = Integer.parseInt(temp)-1;
                    buttons[i][col].setText(""+currentCount);
                }
            }
            for (int j=0;j<NUM_COLS;j++){
                if (gameGrid.isNumReveal(row,j)){
                    temp = (String) buttons[row][j].getText();
                    currentCount = Integer.parseInt(temp)-1;
                    buttons[row][j].setText(""+currentCount);
                }
            }

            numFound++;
            scanUsed.setText("# Scans used: " + numScan);
            foundPanda.setText("Found "+ numFound + " of " + NUM_PANDAS + " Pandas");

            if (numFound==NUM_PANDAS){
                initiateDialog();
            }
        }
        else if (!gameGrid.isReveal(row,col)&&!gameGrid.isPanda(row,col)){
            setRowAnimation(row, col);
            gameGrid.reveal(row,col);
            gameGrid.setNumReveal(row,col);
            int count = gameGrid.getCountPanda(row,col);
            button.setText(""+count);
            numScan++;
            scanUsed.setText("# Scans used: " + numScan);
        }

    }

    private void setRowAnimation(int row, int col){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scan);
        LayoutAnimationController controller = new LayoutAnimationController(anim);
        TableLayout table = (TableLayout) findViewById(R.id.gameTable);
        TableRow clickedrow = (TableRow)table.getChildAt(row);
        clickedrow.setLayoutAnimation(controller);
        for(int i=0; i<NUM_ROWS;i++){
            (buttons[i][col]).setAnimation(anim);
        }
    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void initiateDialog() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        CongratDialog dialog = new CongratDialog();
        dialog.show(manager,"CongratDialog");
    }

}

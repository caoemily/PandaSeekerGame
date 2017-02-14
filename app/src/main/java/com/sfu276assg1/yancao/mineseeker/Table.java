package com.sfu276assg1.yancao.mineseeker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by song on 2017-02-13.
 */

public class Table {
    private boolean [][] table;
    private boolean [][] tableReveal;
    private int NUM_ROW;
    private int NUM_COL;

    public Table(int numRow, int numCol, int numPanda){
        NUM_ROW = numRow;
        NUM_COL = numCol;
        tableReveal = new boolean[numRow][numCol];
        table = new boolean[numRow][numCol];

        // random fill the table with numPanda true entries.
        ArrayList<Integer> list = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i=0; i<numRow*numCol; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<numPanda; i++) {
            set.add(list.get(i));
        }

        for (int i=0;i<numRow;i++){
            for (int j=0;j<numCol;j++){
                int num = i*numCol+j;
                if (set.contains(num)) setPanda(i,j);
            }
        }

    }

    void reveal(int row, int col){
        tableReveal[row][col] = true;
    }

    boolean isReveal(int row, int col){
        return tableReveal[row][col];
    }

    void setPanda(int row, int col){
        table[row][col] = true;
    }

    boolean isPanda(int row, int col){
        return table[row][col];
    }

    int getCountPanda(int row, int col){
        int count=0;
        for (int i=0;i<NUM_COL;i++){
            if (isPanda(row,i)&&!isReveal(row,i)) count++;
        }
        for (int j=0;j<NUM_ROW;j++){
            if (isPanda(j,col)&&!isReveal(j,col)) count++;
        }
        return count;
    }


}

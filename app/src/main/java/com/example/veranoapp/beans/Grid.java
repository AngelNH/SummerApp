package com.example.veranoapp.beans;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.example.veranoapp.ActivityGame;
import com.example.veranoapp.R;

import java.util.Random;

public class Grid {
    //Moves
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    private Square [][] gridSquares;
    private int score;
    private int best;
    private Context context;

    public Grid(int size){
        this.gridSquares = new Square[size][size];
        this.score =0;
        this.best =0;
        for (int i = 0;i<size; i++){
            for (int j = 0;j<size;j++){
                this.gridSquares[i][j] = new Square(0);
            }
        }
    }
    public Grid(int size, Context context,int best){
        this.gridSquares = new Square[size][size];
        this.context = context;
        this.score =0;
        this.best =best;
        for (int i = 0;i<size; i++){
            for (int j = 0;j<size;j++){
                this.gridSquares[i][j] = new Square(0);
            }
        }
    }

    public void setGridSquares(View [] views){
        int i = 0;
        int j = -1;
        for (View view: views) {
            if(i%4==0)j++;
            System.out.println("ITERATION       : " + j + " // "+ i);
            gridSquares[j][i].setView(view);
            i++;
            i %=4;
            j %=4;
        }
    }
    public void setGridSquare(int xAxis, int yAxis,int value){
        gridSquares[xAxis][yAxis].setValue( value );
    }
    public Square getGridSquare(int xAxis, int yAxis){
        return gridSquares[xAxis][yAxis];
    }

    public void addRandomNumber(){
        Random rand = new Random();
        int n = 0;
        int rand_int = rand.nextInt(1000);
        if(rand_int%2==0){
            n = 2;
        }else{
            n = 4;
        }
        int x,y;
        int count =0;
        do {
            rand_int = rand.nextInt(100);
            x = rand_int % 4;
            rand_int = rand.nextInt(100);
            y = rand_int % 4;
            if(gridSquares[x][y].getValue()==0) {
                gridSquares[x][y].setValue(n);
                Animation anim = AnimationUtils.loadAnimation(this.context, R.anim.appear);
                gridSquares[x][y].getView().startAnimation(anim);
                break;
            }
            count ++;
        }while(gridSquares[x][y].getValue()!=0 && count<16);
    }

    public void restartGrid(){
        score =0;
        for(int i=0;i<gridSquares.length;i++)
            for (int j = 0; j < gridSquares.length ; j++) {
                gridSquares[i][j].setValue(0);
            }
    }

    public void swipeOnGrid(int swipe){
        boolean hasMoved = false;
        switch (swipe){
            case RIGHT:
                //TODO RIGHT ANIMATED SWAP
                hasMoved = rightSwipe();
                break;
            case LEFT:
                //TODO LEFT ANIMATED SWIPE
                hasMoved = leftSwipe();
                break;
            case UP:
                //TODO UP ANIMATED SWIPE
                hasMoved = upSwipe();
                break;
            case DOWN:
                //TODO DOWN ANIMATED SWIPE
                hasMoved = downSwipe();
                break;
                default:
                    break;
        }
        if(hasMoved) this.addRandomNumber();
        if(score>best) best = score;
    }

    private boolean rightSwipe(){
        int ite= 0;
        boolean moved= false;
        for (int x = 0;x <gridSquares.length;x++)
            for(int y = gridSquares.length-1; y>=0 ;y--){
                ite = y;
                //move it first
                System.out.println("MY ITERATION HAS TJE FOLLOWING " + ite+ " and the axis :" + x + " , " + y);
                if(gridSquares[x][y].getValue()!=0 && ite < gridSquares.length-1){
                    do{
                        if(ite<gridSquares.length)
                            ite++;
                    }while(gridSquares[x][ite].getValue() == 0 && ite<gridSquares.length-1);
                    System.out.println("now the comparator is "+gridSquares[x][y].getValue()+ " and "+ gridSquares[x][ite].getValue());
                    if(gridSquares[x][y].getValue() == gridSquares[x][ite].getValue() || gridSquares[x][ite].getValue()==0 ){
                        int res = gridSquares[x][y].getValue() + gridSquares[x][ite].getValue();
                        if(res != gridSquares[x][y].getValue() ) score += res;
                        gridSquares[x][ite].setValue(res);
                        gridSquares[x][y].setValue(0);
                        if(ite != y)moved = true;
                        System.out.println("FOUNDED AND ADDED " + res + " with ite and y " + ite + " : "+y);
                    }else if(gridSquares[x][ite].getValue()!=0 && ite-1 != y) {
                        int aux = gridSquares[x][y].getValue();
                        gridSquares[x][y].setValue(0);
                        gridSquares[x][ite - 1].setValue(aux);
                        System.out.println("NOT FOUNDED 404 :::: "+ite +" and "+ y);
                        if(ite-1 != y)moved = true;
                    }

                }
            }
        return moved;
    }

    private boolean leftSwipe(){
        int ite= 0;
        boolean moved  = false;
        for (int x = 0;x <gridSquares.length;x++)
            for(int y = 0;y <gridSquares.length ;y++){
                ite = y;
                //move it first
                System.out.println("LEFT ITER " + ite+ " and the axis :" + x + " , " + y);
                if(gridSquares[x][y].getValue()!=0 && ite>0){
                    do{
                        if(ite>0)
                            ite--;
                    }while(gridSquares[x][ite].getValue() == 0 && ite>0);
                    System.out.println("now the comparator is "+gridSquares[x][y].getValue()+ " and "+ gridSquares[x][ite].getValue());
                    if(gridSquares[x][y].getValue() == gridSquares[x][ite].getValue() || gridSquares[x][ite].getValue()==0){
                        int res = gridSquares[x][y].getValue() + gridSquares[x][ite].getValue();
                        if(res != gridSquares[x][y].getValue() ) score += res;
                        gridSquares[x][ite].setValue(res);
                        gridSquares[x][y].setValue(0);
                        System.out.println("FOUNDED AND ADDED");
                        if(ite != y)moved = true;
                    }else if(gridSquares[x][ite].getValue()!=0 && ite+1 != y){
                        int aux = gridSquares[x][y].getValue();
                        gridSquares[x][y].setValue(0);
                        gridSquares[x][ite+1].setValue(aux);
                        System.out.println("NOT FOUNDED 404");
                        if(ite+1 != y)moved = true;
                    }
                }
            }
        return moved;
    }

    private boolean downSwipe(){
        int ite= 0;
        boolean moved = false;
        for (int y = 0;y <gridSquares.length;y++)
            for(int x = gridSquares.length-1; x>=0 ;x--){
                ite = x;
                //move it first
                System.out.println("MY ITERATION HAS TJE FOLLOWING " + ite+ " and the axis :" + x + " , " + y);
                if(gridSquares[x][y].getValue()!=0 && ite < gridSquares.length-1){
                    do{
                        if(ite<gridSquares.length)
                            ite++;
                    }while(gridSquares[ite][y].getValue() == 0 && ite<gridSquares.length-1);
                    System.out.println("now the comparator is "+gridSquares[x][y].getValue()+ " and "+ gridSquares[ite][y].getValue());
                    if(gridSquares[x][y].getValue() == gridSquares[ite][y].getValue() || gridSquares[ite][y].getValue()==0){
                        int res = gridSquares[x][y].getValue() + gridSquares[ite][y].getValue();
                        if(res != gridSquares[x][y].getValue() ) score += res;
                        gridSquares[ite][y].setValue(res);
                        gridSquares[x][y].setValue(0);
                        System.out.println("FOUNDED AND ADDED");
                        if(ite != x)moved = true;
                    }else if(gridSquares[ite][y].getValue()!=0 && ite-1 != x){
                        int aux = gridSquares[x][y].getValue();
                        gridSquares[x][y].setValue(0);
                        gridSquares[ite-1][y].setValue(aux);
                        System.out.println("NOT FOUNDED 404");
                        if(ite-1 != x)moved = true;
                    }
                }
            }
        return moved;
    }

    private boolean upSwipe(){
        int ite= 0;
        boolean moved = false;
        for (int y = 0;y <gridSquares.length;y++)
            for(int x = 0;x <gridSquares.length ;x++){
                ite = x;
                //move it first
                System.out.println("LEFT ITER " + ite+ " and the axis :" + x + " , " + y);
                if(gridSquares[x][y].getValue()!=0 && ite>0){
                    do{
                        if(ite>0)
                            ite--;
                    }while(gridSquares[ite][y].getValue() == 0 && ite>0);
                    System.out.println("now the comparator is "+gridSquares[x][y].getValue()+ " and "+ gridSquares[ite][y].getValue());
                    if(gridSquares[x][y].getValue() == gridSquares[ite][y].getValue() || gridSquares[ite][y].getValue()==0){
                        int res = gridSquares[x][y].getValue() + gridSquares[ite][y].getValue();
                        if(res != gridSquares[x][y].getValue() ) score += res;
                        gridSquares[ite][y].setValue(res);
                        gridSquares[x][y].setValue(0);
                        System.out.println("FOUNDED AND ADDED");
                        if(ite != x)moved = true;
                    }else if(gridSquares[ite][y].getValue()!=0 && ite+1 != x){
                        int aux = gridSquares[x][y].getValue();
                        gridSquares[x][y].setValue(0);
                        gridSquares[ite+1][y].setValue(aux);
                        System.out.println("NOT FOUNDED 404");
                        if(ite+1 != x)moved = true;
                    }
                }
            }
        return moved;
    }


    public int getBest(){
        return best;
    }
    public int getScore(){
        return score;
    }

}

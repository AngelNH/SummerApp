package com.example.veranoapp.beans;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;


import androidx.annotation.ColorLong;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.veranoapp.ActivityGame;
import com.example.veranoapp.R;

/**
 * Object for each Square in the grid
 */
public class Square {
    //colors
    public static Context context;

    final static int COLORN = Color.parseColor("#cdc1b5") ;
    final static int COLOR2 = Color.parseColor("#FAEEE2");
    final static int COLOR4 = Color.parseColor("#F7D7B4");
    final static int COLOR8 = Color.parseColor("#F5A554");
    final static int COLOR16= Color.parseColor("#F38F29");
    final static int COLOR32 = Color.parseColor("#F86E42");
    final static int COLOR64= Color.parseColor( "#F74D18");
    final static int COLOR128= Color.parseColor("#F7EE52");
    final static int COLOR256= Color.parseColor("#F8EE39");
    final static int COLOR512= Color.parseColor("#e6b800");
    final static int COLOR1024= Color.parseColor("#EEC005");
    final static int COLOR2048= Color.parseColor("#ffcc00");


    //Attributes
    private int value;
    private int color;
    private Button view;


    public Square(int value) {
        setValue(value);
    }

    public Square(int value,View view) {
        setValue(value);
        setView(view);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if(view!= null){
            setColor(value);
            if(value !=0)this.view.setText(""+value);
        }
    }

    public int getColor() {
        return color;
    }

    private void setColor(int number) {
        switch (number) {
            case 0:
                this.color = COLORN;
                view.setText("");
                view.setTextSize(40);
                break;
            case 2:
                this.color = COLOR2;
                view.setTextColor(Color.BLACK);
                view.setTextSize(40);
                break;
            case 4:
                this.color = COLOR4;
                view.setTextColor(Color.BLACK);
                view.setTextSize(40);
                break;
            case 8:
                this.color = COLOR8;
                view.setTextColor(Color.WHITE);
                view.setTextSize(40);
                break;
            case 16:
                this.color = COLOR16;
                view.setTextColor(Color.WHITE);
                view.setTextSize(40);
                break;
            case 32:
                this.color = COLOR32;
                view.setTextColor(Color.WHITE);
                view.setTextSize(40);
                break;
            case 64:
                this.color = COLOR64;
                view.setTextColor(Color.WHITE);
                view.setTextSize(40);
                break;
            case 128:
                this.color = COLOR128;
                view.setTextColor(Color.WHITE);
                view.setTextSize(35);
                break;
            case 256:
                this.color = COLOR256;
                view.setTextColor(Color.WHITE);
                view.setTextSize(35);
                break;
            case 512:
                this.color = COLOR512;
                view.setTextColor(Color.WHITE);
                view.setTextSize(35);
            case 1024:
                this.color = COLOR1024;
                view.setTextColor(Color.WHITE);
                view.setTextSize(30);
                break;
            case 2048:
                this.color = COLOR2048;
                view.setTextColor(Color.WHITE);
                view.setTextSize(30);
                break;
                default:
                  this.color = COLORN;
                    view.setText("");
                    view.setTextSize(40);
                  break;
        }
        this.view.setBackgroundColor(this.color);
    }

    public View getView(){
        return view;
    }

    public void setView(View view){
        this.view =(Button) view;
        if(value != 0){
            this.view.setText(""+value);
            setColor(value);
        }
    }
}

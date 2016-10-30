package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by danny.hu on 9/28/16.
 */

public class Rock {
    private Vector2 positon;
    private int width;
    private int height;

    public Rock(float x, float y, int width, int height) {
        this.positon = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public float getX(){
        return this.positon.x;
    }
    public float getY(){
        return this.positon.y;
    }
}
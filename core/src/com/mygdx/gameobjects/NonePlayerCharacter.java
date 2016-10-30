package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by danny.hu on 9/28/16.
 */

public class NonePlayerCharacter {
    private Vector2 positon;
    private int width;
    private int height;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 originPoint;
    private Vector2 destination;
    public NonePlayerCharacter(float x, float y, int width, int height) {
        this.positon = new Vector2(x, y);
        this.originPoint = new Vector2(x,y);
        this.destination = new Vector2(x+150,y);
        this.width = width;
        this.height = height;
        this.acceleration = new Vector2(0,0);
        this.velocity = new Vector2(30,0);
    }
    public void update(float delta){
        this.walk(delta);
    }
    public void walk(float delta){
        this.velocity.add(this.acceleration.cpy().scl(delta));
        if (this.positon.x >= this.destination.x){
            this.velocity = new Vector2(-30,0);
        }else if (this.positon.x <= this.originPoint.x){
            this.velocity = new Vector2(30,0);
        }
        this.positon.add(this.velocity.cpy().scl(delta));
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

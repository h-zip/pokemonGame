package com.mygdx.gameobjects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by danny.hu on 9/28/16.
 */

public class Player {
    private Vector2 positon;
    private int width;
    private int height;
    private Vector2 center;
    private Vector2 leftupPoint;
    private Vector2 rightdownPoint;
    private Vector2 rightupPoint;
    private Vector2 velocity;
    private Vector2 acceleration;
    private boolean isWalking;
    private int direction;
    private float v;
    private Rectangle rectangle;
    public Player(float x,float y,int width,int height){
        this.positon = new Vector2(x,y);
        this.width = width;
        this.height = height;
        this.v = 3;
        this.center = new Vector2(x+width/2,y+height/2);
        this.leftupPoint = new Vector2(x,y+height);
        this.rightupPoint = new Vector2(x+width,y+height);
        this.rightdownPoint = new Vector2(x+width,y);
        this.acceleration = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        this.isWalking = false;
        this.direction = -1;
        this.rectangle = new Rectangle(x,y,width,height);


    }
    public void update(float delta){
        if (this.isWalking){
            this.walk(this.direction);
        }
    }
    public void walk(int direction){
        if (direction == Input.Keys.LEFT){
                this.positon.add(-1*this.v,0);
        }else if (direction == Input.Keys.RIGHT){
                this.positon.add(this.v,0);
        }else if (direction == Input.Keys.UP){
                this.positon.add(0,1*this.v);
        }else if(direction == Input.Keys.DOWN){
                this.positon.add(0,-1*this.v);
        }
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
    public float getCenterX(){
        return this.getCenter().x;
    }
    public float getCenterY(){
        return this.getCenter().y;
    }
    public Vector2 getPositon(){
        return this.positon;
    }
    public Vector2 getCenter(){
        this.center.set(this.positon.x+this.width/2,this.positon.y+this.height/2);
        return this.center;
    }
    public void setWalking(boolean isWalking){
        this.isWalking = isWalking;
    }
    public boolean isWalking() {
        return this.isWalking;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }
    public int getDirection(){
        return this.direction;
    }
    public float getV(){
        return this.v;
    }
    public Vector2 getLeftupPoint(){
        this.leftupPoint.set(this.positon.x,this.positon.y+this.height);
        return this.leftupPoint;
    }
    public Vector2 getRightdownPoint(){
        this.rightdownPoint.set(this.positon.x+this.width,this.positon.y);
        return this.rightdownPoint;
    }
    public Vector2 getRightupPoint(){
        this.rightupPoint.set(this.positon.x+this.width,this.positon.y+this.height);
        return this.rightupPoint;
    }
    public Rectangle getRectangle(){
        this.rectangle.set(this.positon.x,this.positon.y,this.width,this.height);
        return this.rectangle;
    }
}

package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by danny.hu on 24/10/2016.
 */

public class WorldCell {
    public enum CellType{
        BLOCK,GROUND
    }
    public int layerType;
    public String cellType;
    public Polygon poly;
    public String text;
    public WorldCell(){
        this.layerType = -99;
        this.cellType = new String();
        this.text = new String();
    }
    public void setPoly(Rectangle rectangle){
        this.poly = new Polygon(new float[] { 0, 0, rectangle.width, 0, rectangle.width,
                rectangle.height, 0, rectangle.height });
        this.poly.setPosition(rectangle.x, rectangle.y);
    }
    public void setPoly(Polygon poly){
        this.poly = poly;
    }
}

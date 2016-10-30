package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gameassets.AssetLoader;
import com.mygdx.gameobjects.Player;
import com.mygdx.gameobjects.WorldCell;
import com.mygdx.gameworlds.GameWorld;
import java.util.ArrayList;

/**
 * Created by danny.hu on 9/30/16.
 */

public class CollideHandler {
    private TiledMap map;
    private WorldCell[][] cells;
    private Player player;
    private ArrayList<WorldCell> collideArrayList;
    private WorldCell collideCell;
    public CollideHandler(GameWorld gameWorld) {
        this.map = AssetLoader.map;
        this.collideArrayList = new ArrayList<WorldCell>();
        this.initCells();
        this.player = gameWorld.getPlayerMap().get("player");
        Gdx.app.log("size",String.valueOf(this.collideArrayList.size()));
    }

    public void initCells(){
        for (MapLayer mapLayer : this.map.getLayers()){
          int layerType = mapLayer.getProperties().get("type",int.class);
            if (layerType == 0){
                TiledMapTileLayer tileLayer = (TiledMapTileLayer)mapLayer;
                this.cells = new WorldCell[tileLayer.getWidth()][tileLayer.getHeight()];
                for (int y = 0; y <tileLayer.getHeight(); y++) {
                    for (int x = 0; x <tileLayer.getWidth(); x++) {
                        WorldCell worldCell = new WorldCell();
                        worldCell.setPoly(new Rectangle(x*32,y*32,32,32));
                        worldCell.layerType = layerType;
                        this.cells[x][y] = worldCell;
                    }
                }
            }
        }
        for (MapLayer mapLayer : this.map.getLayers()){
            int layerType = mapLayer.getProperties().get("type",int.class);
            if (layerType>0){
                TiledMapTileLayer tileLayer = (TiledMapTileLayer)mapLayer;
                for (int y = 0; y <tileLayer.getHeight(); y++) {
                    for (int x = 0; x <tileLayer.getWidth(); x++) {
                        TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);
                        if (cell!=null){
                            this.cells[x][y].layerType = layerType;
                            this.collideArrayList.add(this.cells[x][y]);
                        }
                    }
                }
            }else if (layerType ==-1){
                for (MapObject mapObject : mapLayer.getObjects()){
                    PolygonMapObject polygonMapObject = (PolygonMapObject)mapObject;
                    //TiledMapTileMapObject tiledMapTileMapObject = (TiledMapTileMapObject)mapObject;
                    Polygon poly = polygonMapObject.getPolygon();

                    WorldCell worldCell = new WorldCell();
                    worldCell.setPoly(poly);
                    worldCell.layerType = layerType;
                    if (polygonMapObject.getProperties().get("type",String.class)!=null){
                        worldCell.cellType = polygonMapObject.getProperties().get("type",String.class);
                    }
                    if (polygonMapObject.getProperties().get("text",String.class)!=null){
                        worldCell.text = polygonMapObject.getProperties().get("text",String.class);
                    }
                    this.collideArrayList.add(worldCell);
                }
            }
        }
    }

    public boolean isCollided(int direction){
        boolean _isCoollied = false;
        for (WorldCell worldCell : this.collideArrayList){
            _isCoollied = CollideHelper.isCollision( worldCell.poly,this.player.getRectangle());
            //Gdx.app.log("collide",String.valueOf(_isCoollied));
            if (_isCoollied){
                Gdx.app.log("player rect",String.valueOf(this.player.getRectangle()));
                //Gdx.app.log("cell rect",String.valueOf(worldCell.poly));
                this.collideCell = worldCell;
                if (this.collideCell.cellType.equals("NPC")){
                    Gdx.app.log("NPC",this.collideCell.text);

                }
                return _isCoollied;
            }
        }
        return _isCoollied;

    }
    public void handleCollide(){
        if (this.isCollided(this.player.getDirection())){
            Gdx.app.log("collided","true");
            if (this.player.getDirection() == Input.Keys.RIGHT){
//                Gdx.app.log("up y",String.valueOf(this.player.getLeftupPoint().x));
//                Gdx.app.log("up y",String.valueOf(this.player.getRightupPoint().x));
//
//                Gdx.app.log("up y",String.valueOf(this.collideCell.rectangle.getX()));
//                Gdx.app.log("down y",String.valueOf(this.collideCell.rectangle.getX()+this.collideCell.rectangle.getWidth()));
                //float dX = this.player.getRightupPoint().x - (this.collideCell.rectangle.getX());
                float dX = this.player.getV();
                Gdx.app.log("dx",String.valueOf(dX));
                this.player.getPositon().add(-dX,0);
            }else if (this.player.getDirection() == Input.Keys.LEFT){
                //float dX = this.player.getX() - (this.collideCell.rectangle.getX()+this.collideCell.rectangle.getWidth());
                float dX = -1 * this.player.getV();
                Gdx.app.log("dx",String.valueOf(dX));
                this.player.getPositon().add(-dX,0);
            }else if (this.player.getDirection() == Input.Keys.DOWN){
                //float dY =  this.player.getY() - (this.collideCell.rectangle.getY()+this.collideCell.rectangle.getHeight());
                float dY = -1 * this.player.getV();
                Gdx.app.log("dy",String.valueOf(dY));
                this.player.getPositon().add(0,-dY);
            }else if(this.player.getDirection() == Input.Keys.UP){
                //float dY =  this.player.getLeftupPoint().y - this.collideCell.rectangle.getY();
                float dY = this.player.getV();
                Gdx.app.log("dy",String.valueOf(dY));
                this.player.getPositon().add(0,-dY);
            }
        }
    }
    //    public void initCells1(){
//        for (MapLayer mapLayer : this.map.getLayers()){
//            if (mapLayer.getName().equals("background")){
//                TiledMapTileLayer tileLayer = (TiledMapTileLayer)mapLayer;
//                this.cells = new WorldCell[tileLayer.getWidth()][tileLayer.getHeight()];
//            }
//            if (!mapLayer.getProperties().get("canPass",Boolean.class)){
//                TiledMapTileLayer tileLayer = (TiledMapTileLayer)mapLayer;
//                for (int y = 0; y <tileLayer.getHeight(); y++) {
//                    for (int x = 0; x <tileLayer.getWidth(); x++) {
//                        TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);
//                        WorldCell worldCell = new WorldCell();
//                        worldCell.rectangle = new Rectangle(x*32,y*32,32,32);
//                        this.cells[x][y] = worldCell;
//                        this.cells[x][y].cellType = WorldCell.CellType.GROUND;
//                        if (cell != null||(cell == null && this.cells[x][y].cellType == WorldCell.CellType.BLOCK)) {
//                            this.cells[x][y].cellType = WorldCell.CellType.BLOCK;
//                            this.arrayList.add(this.arrayList.size(),this.cells[x][y]);
//
//                        }else if (cell == null && this.cells[x][y].cellType != WorldCell.CellType.BLOCK){
//                            this.cells[x][y].cellType = WorldCell.CellType.GROUND;
//                        }
//                        Gdx.app.log("x",String.valueOf(x));
//                        Gdx.app.log("y",String.valueOf(y));
//                        Gdx.app.log("cell",this.cells[x][y].cellType.toString());
//                    }
//                }
//            }
//        }
//    }
//    public boolean isCollided(int direction){
//        boolean _isCoollied = false;
////        if (direction ==Input.Keys.LEFT){
////            _isCoollied = this.cells[(int)this.player.getX()/32][(int)this.player.getY()/32].cellType!=WorldCell.CellType.GROUND || this.cells[(int)this.player.getLeftupPoint().x/32][(int)this.player.getLeftupPoint().y/32].cellType!=WorldCell.CellType.GROUND;
////
////        }else if (direction ==Input.Keys.RIGHT){
////            _isCoollied = this.cells[(int)this.player.getX()/32+1][(int)this.player.getY()/32].cellType!=WorldCell.CellType.GROUND;
////        }else if (direction ==Input.Keys.UP){
////            _isCoollied = this.cells[(int)this.player.getX()/32][(int)this.player.getY()/32+1].cellType!=WorldCell.CellType.GROUND;
////        }else if (direction ==Input.Keys.DOWN){
////            _isCoollied = this.cells[(int)this.player.getX()/32][(int)this.player.getY()/32].cellType!=WorldCell.CellType.GROUND;
////        }
//        return _isCoollied;
//
//    }
//     public void handleCollide(){
////        if (this.isCollided(this.player.getDirection())){
////            Gdx.app.log("collided","true");
////            if (this.player.getDirection() == Input.Keys.LEFT){
////                float dX = ((int)this.player.getX()/32+1) *32 - this.player.getX();
////                Gdx.app.log("dx",String.valueOf(dX));
////                this.player.getPositon().add(dX,0);
////            }else if (this.player.getDirection() == Input.Keys.RIGHT){
////                float dX = ((int)this.player.getX()/32) *32 - this.player.getX();
////                Gdx.app.log("dx",String.valueOf(dX));
////                this.player.getPositon().add(dX,0);
////            }else if (this.player.getDirection() == Input.Keys.UP){
////                float dY =  ((int)this.player.getY()/32) *32 - this.player.getY();
////                Gdx.app.log("dy",String.valueOf(dY));
////                this.player.getPositon().add(0,dY);
////            }else if(this.player.getDirection() == Input.Keys.DOWN){
////                float dY =  ((int)this.player.getY()/32+1) *32 - this.player.getY();
////                Gdx.app.log("dy",String.valueOf(dY));
////                this.player.getPositon().add(0,dY);
////            }
////        }
//    }
}

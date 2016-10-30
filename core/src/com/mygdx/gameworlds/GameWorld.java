package com.mygdx.gameworlds;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.gameobjects.NonePlayerCharacter;
import com.mygdx.gameobjects.Player;
import com.mygdx.gameobjects.Rock;
import com.mygdx.helpers.CollideHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by danny.hu on 9/28/16.
 */

public class GameWorld {

    private HashMap <String,Player>playerMap;
    private HashMap <String,NonePlayerCharacter>npcMap;
    private HashMap <String,Rock>barrierMap;
    private int worldWidth;
    private int worldHeight;
    private int unit;
    private CollideHandler collideHandler;
    public  GameWorld(int screenWidth,int screenHeight){
        this.unit = 32;
        this.worldHeight = 3 * screenHeight;
        this.worldWidth = 3 * screenWidth;

        Player player = new Player(200,100,1*this.unit,1*this.unit);
        this.playerMap = new HashMap<String,Player>();
        this.playerMap.put("player",player);
        NonePlayerCharacter npc1 = new NonePlayerCharacter(20,20,1*this.unit,1*this.unit);
        NonePlayerCharacter npc2 = new NonePlayerCharacter(80,120,1*this.unit,1*this.unit);
        NonePlayerCharacter npc3 = new NonePlayerCharacter(120,220,1*this.unit,1*this.unit);
        this.npcMap = new HashMap<String,NonePlayerCharacter>();
//        this.npcMap.put("npc1",npc1);
//        this.npcMap.put("npc2",npc2);
//        this.npcMap.put("npc3",npc3);

        Rock rock1 = new Rock(20,150,5*this.unit,5*this.unit);
        Rock rock2 = new Rock(170,150,5*this.unit,5*this.unit);
        Rock rock3 = new Rock(220,150,5*this.unit,5*this.unit);
        this.barrierMap = new HashMap<String,Rock>();
//        this.barrierMap.put("rock1",rock1);
//        this.barrierMap.put("rock2",rock2);
//        this.barrierMap.put("rock3",rock3);

        this.collideHandler = new CollideHandler(this);
    }
    public void update(float delta){
        Iterator<Map.Entry<String,Player>> iterator1 = this.playerMap.entrySet().iterator();
        Map.Entry<String,Player> entry1;
        while (iterator1.hasNext()) {
            entry1 = iterator1.next();
            Player t = entry1.getValue();
            t.update(delta);
        }
        Iterator<Map.Entry<String,NonePlayerCharacter>> iterator2 = this.npcMap.entrySet().iterator();
        Map.Entry<String,NonePlayerCharacter> entry2;
        while (iterator2.hasNext()) {
            entry2 = iterator2.next();
            NonePlayerCharacter t = entry2.getValue();
            t.update(delta);
        }
        this.collideHandler.handleCollide();

    }
    public HashMap<String,Player> getPlayerMap(){
            return this.playerMap;
    }
    public HashMap<String,NonePlayerCharacter> getNpcMap(){
        return this.npcMap;
    }
    public HashMap<String,Rock> getBarrierMap(){
        return this.barrierMap;
    }
    public int getWorldWidth(){
        return this.worldWidth;
    }
    public int getWorldHeight(){
        return this.worldHeight;
    }
}

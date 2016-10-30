package com.mygdx.gameassets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by danny.hu on 9/30/16.
 */

public class AssetLoader {
    public static TiledMap map;
    public static void load(){
        map = new TmxMapLoader().load("map3.tmx");
    }
}

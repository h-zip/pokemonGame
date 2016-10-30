package com.mygdx.gamerenderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.gameobjects.NonePlayerCharacter;
import com.mygdx.gameobjects.Player;
import com.mygdx.gameobjects.Rock;
import com.mygdx.gameworlds.GameWorld;
import com.mygdx.gameassets.AssetLoader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by danny.hu on 9/28/16.
 */

public class GameRenderer {
    private Stage stage;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private GameWorld gameWorld;
    private HashMap <String,Player>playerMap;
    private HashMap <String,NonePlayerCharacter>npcMap;
    private HashMap <String,Rock>barrierMap;
    private int worldWidth;
    private int worldHeight;
    private TiledMap map;
    public GameRenderer(GameWorld gameWorld,int screenWidth,int screenHeight){
        this.gameWorld = gameWorld;
        this.worldHeight = gameWorld.getWorldHeight();
        this.worldWidth = gameWorld.getWorldWidth();
        this.initGameObjects();
        this.initAssets();
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.map);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,screenWidth/2,screenHeight/2);
        this.camera.position.set(this.playerMap.get("player").getCenterX(),this.playerMap.get("player").getCenterY(),0);
        this.camera.zoom = 1f;
        this.camera.update();
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(this.camera.combined);
        this.spriteBatch = new SpriteBatch();
        this.stage = new Stage(new StretchViewport(screenWidth,screenHeight,this.camera));


    }
    private void initGameObjects(){
        this.playerMap = this.gameWorld.getPlayerMap();
        this.npcMap = this.gameWorld.getNpcMap();
        this.barrierMap = this.gameWorld.getBarrierMap();
    }
    private void initAssets(){
        this.map = AssetLoader.map;
    }
    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //this.stage.act();
        //this.stage.draw();
        this.camera.position.set(this.playerMap.get("player").getCenterX(),this.playerMap.get("player").getCenterY(),0);
        this.camera.update();
        this.tiledMapRenderer.setView(this.camera);
        this.tiledMapRenderer.render();
        this.shapeRenderer.setProjectionMatrix(this.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

//        shapeRenderer.setColor(Color.WHITE);
//        shapeRenderer.rect(0, 0,this.worldWidth, this.worldHeight);

        shapeRenderer.setColor(Color.BLUE);
        Iterator<Map.Entry<String,Rock>> iterator1= this.barrierMap.entrySet().iterator();
        Map.Entry<String,Rock> entry1;
        while (iterator1.hasNext()) {
            entry1 = iterator1.next();
            Rock t = entry1.getValue();
            shapeRenderer.rect(t.getX(),t.getY(),t.getWidth(),t.getHeight());
        }

        shapeRenderer.setColor(Color.RED);
        Iterator<Map.Entry<String,NonePlayerCharacter>> iterator2 = this.npcMap.entrySet().iterator();
        Map.Entry<String,NonePlayerCharacter> entry2;
        while (iterator2.hasNext()) {
            entry2 = iterator2.next();
            NonePlayerCharacter t = entry2.getValue();
            shapeRenderer.rect(t.getX(),t.getY(),t.getWidth(),t.getHeight());
        }


        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(this.playerMap.get("player").getX(),this.playerMap.get("player").getY(),this.playerMap.get("player").getWidth(),this.playerMap.get("player").getHeight());

        // Tells the shapeRenderer to finish rendering
        // We MUST do this every time.
        shapeRenderer.end();

    }

}
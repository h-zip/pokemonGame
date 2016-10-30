package com.mygdx.gamescreens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.gamerenderers.GameRenderer;
import com.mygdx.gameworlds.GameWorld;
import com.mygdx.helpers.InputHandler;

/**
 * Created by danny.hu on 9/28/16.
 */

public class GameScreen implements Screen{
    private GameRenderer gameRenderer;
    private GameWorld gameWorld;
    public GameScreen(){
        Gdx.app.log("GameScreen","setted");

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        Gdx.app.log("screenWidth",String.valueOf(screenWidth));
        Gdx.app.log("screenHeight",String.valueOf(screenHeight));
        this.gameWorld = new GameWorld(screenWidth,screenHeight);
        this.gameRenderer = new GameRenderer(this.gameWorld,screenWidth,screenHeight);
        Gdx.input.setInputProcessor(new InputHandler(this.gameWorld));
    }
    @Override
    public void show (){
        Gdx.app.log("GameScreen","show");

    }
    /** Called when the screen should render itself.
     * @param delta The time in seconds since the last render. */
    @Override
    public void render (float delta){
        this.gameWorld.update(delta);
        this.gameRenderer.render();
    }
    @Override
    public void resize (int width, int height){
        Gdx.app.log("GameScreen","resize");
    }
    @Override
    public void pause (){
        Gdx.app.log("GameScreen","pause");
    }
    @Override
    public void resume (){
        Gdx.app.log("GameScreen","resume");
    }
    /** Called when this screen is no longer the current screen for a {@link Game}. */
    @Override
    public void hide (){
        Gdx.app.log("GameScreen","hide");
    }
    /** Called when this screen should release all resources. */
    @Override
    public void dispose (){
        Gdx.app.log("GameScreen","dispose");
    }
}

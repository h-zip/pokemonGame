package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.gameobjects.Player;
import com.mygdx.gameworlds.GameWorld;

/**
 * Created by danny.hu on 9/29/16.
 */

public class InputHandler implements InputProcessor {
    private GameWorld gameWorld;
    private Player player;
    private int pressedNum;
    public InputHandler(GameWorld gameWorld){
        this.pressedNum = 0;
        this.gameWorld = gameWorld;
        this.player = gameWorld.getPlayerMap().get("player");
    }
    @Override
    public boolean keyDown (int keycode){
        this.pressedNum++;
        if (this.pressedNum>0){
            this.player.setWalking(true);
            this.player.setDirection(keycode);
        }
        return true;
    }

    @Override
    public boolean keyUp (int keycode){
        this.pressedNum--;
        if (this.pressedNum==0){
            this.player.setWalking(false);
            this.player.setDirection(-2);
        }
        return true;
    }

    @Override
    public boolean keyTyped (char character){return false;}

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button){return false;}

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button){return false;}

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer){return false;}

    @Override
    public boolean mouseMoved (int screenX, int screenY){return false;}

    @Override
    public boolean scrolled (int amount){return false;}
}

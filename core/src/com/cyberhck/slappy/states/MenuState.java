package com.cyberhck.slappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cyberhck.slappy.Slappy;

public class MenuState extends State {
    private Texture background;
    private Texture PlayBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Slappy.WIDTH / 2, Slappy.HEIGHT / 2);
        background = new Texture("bg.png");
        PlayBtn = new Texture("play.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(PlayBtn, cam.position.x - PlayBtn.getWidth()/2 , cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        PlayBtn.dispose();
    }
}

package com.cyberhck.slappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cyberhck.slappy.Slappy;
import com.cyberhck.slappy.sprites.Bird;
import com.cyberhck.slappy.sprites.Tube;

public class PlayState extends State {

    private static final int TUBE_SPACING = 175;
    private static final int TUBE_COUNT = 4;
    private Array<Tube> tubes;

    private Texture background;

    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Bird bird;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 100);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2)+ground.getWidth() , 0);

        cam.setToOrtho(false, Slappy.WIDTH / 2, Slappy.HEIGHT / 2);
        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for(Tube tube : tubes){
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
            if(tube.collides(bird.getBounds())){
                gsm.set(new MenuState(gsm));
                break;
            }
        }
        updateGround();
        if(bird.getPosition().y <= ground.getHeight()){
            gsm.set(new PlayState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x - (cam.viewportWidth/2), 0, Slappy.WIDTH, Slappy.HEIGHT);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        background.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
    }
    private void updateGround(){
        if((cam.position.x - cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if((cam.position.x - cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);

    }
}

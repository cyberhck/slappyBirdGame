package com.cyberhck.slappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 80;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int TUBEGAP = 150;
    private static final int LOWEST_OPENING = 120;
    private Rectangle boundsTop, boundsBot;

    public Tube(float x){
        topTube = new Texture("top_tube.png");
        bottomTube = new Texture("bottom_tube.png");

        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBEGAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBEGAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
        bottomTube.dispose();
        topTube.dispose();
    }

    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBEGAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBEGAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
}

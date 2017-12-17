package game;

import java.awt.*;
/**
 * Created by Andrej on 12.12.2017.
 */
public abstract class Entity {

    protected int x;
    protected int y;

    protected Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void aplay();
    public abstract void render(Graphics2D g);
}
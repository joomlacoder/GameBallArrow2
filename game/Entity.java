package game;

import java.awt.*;
/**
 * Created by Andrej on 12.12.2017.
 */
public abstract class Entity {

   /* protected float x;
    protected float y;

    protected Entity(float x, float y){
        this.x = x;
        this.y = y;
    }*/

    public abstract void aplay();
    public abstract void render(Graphics2D g);
}
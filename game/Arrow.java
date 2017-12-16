package game;

import java.awt.*;

class Arrow extends Entity{

    private static final int        HALF_PLAYER             = 13;
    private static final int        SPEAD                   = 10;
    private static final int        START_Y                 =  650;
    private static final int        HEDTH                   = 400;

    private int x = -100;
    private int y = START_Y;
    private boolean fire = false;

    public void fire(int x) {
        fire = true;
        this.x = x + HALF_PLAYER;
        this.y = START_Y;
    }

    public void aplay(){
        if(fire)
            setY(y - SPEAD);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        if (y < - HEDTH)
            fire = false;
    }

    public int getH() {
        return HEDTH;
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public void render(Graphics2D g) {
            g.setColor(new Color(0xff00ff00));
            g.fillRoundRect(x, y, 2, HEDTH, 10, 10);
    }
}
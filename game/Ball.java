package game;

import java.awt.*;
import java.io.Serializable;

class Ball extends Entity implements Serializable{

    private static final int        ACCELERATION            = 1;
    private static final int        END_WINDOW_Y            = Game.HIGHT;
    private static final int        END_WINDOW_X            = Game.WEIDTH;
    private static final int        START_WINDOW_X          = 0;

    private int d;
    private int speed;
    private int direction = 3;
    private int oldX;
    private boolean turn = false;

    public void aplay(){
        setSpeed(speed + ACCELERATION);
        setY(y + speed);
        setX(x + direction);
    }

    public Ball(int x, int y, int d, int direction, int speed) {
        super(x,y);
        this.d = d;
        this.direction = direction;
        this.speed = speed;
        oldX = x;
    }

    void doTurn(){
        if(Math.abs(oldX - x) > d*2)
            turn = true;
    }

    @Override //Лучше через конструктор клонирования
    protected Ball clone() {
        return new Ball(x, y, d, direction, speed);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x > END_WINDOW_X - d || x < START_WINDOW_X) {
            reDirection();
            setX(x + direction);
        }else
            this.x = x;
    }

    private void reDirection(){
        direction *= -1;
    }

    private void reSpeed(){
        speed *= -1;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if(y > END_WINDOW_Y - d){
            reSpeed();
            setY(END_WINDOW_Y - d);
        } else {
            this.y = y;
        }
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isTurn() {
        return turn;
    }


    public void render(Graphics2D g) {
            g.setColor(new Color(0xff0000ff));
            g.fillOval(x, y, d, d);

    }
}
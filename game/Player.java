package game;

import java.awt.*;

class Player extends Entity{

    private static final int        Y                   = 650;
    private static final int        WINDTH              = 30;
    private static final int        HIGHT               = 60;
    private static final int        SPEAD               = 12;
    private static final int        START_WINDOW        = 0;
    private static final int        END_WINDOW          = Game.WEIDTH;

    Heading heading;


    private enum Heading {
        LEFT,
        RITE,
        NON
    }

    protected Player(){
        super(600, Y);
    }

    protected Player(int x, int y) {
        super(x, y);
    }


    public void aplay(){
        if(isLeft())
            setX(x - SPEAD);
        if(isRight())
            setX(x + SPEAD);
    }

    public int getWINDTH() {
        return WINDTH;
    }

    public int getHIGHT() {
        return HIGHT;
    }

    public boolean isLeft() {
        return heading == Heading.LEFT;
    }

    public void setLeft() {
        heading = Heading.LEFT;
    }

    public boolean isRight() {
        return heading == Heading.RITE;
    }

    public void setRight() {
        heading = Heading.RITE;
    }

    public void setNon(){
        heading = Heading.NON;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x > END_WINDOW - WINDTH)
            this.x = END_WINDOW - WINDTH;
        else if(x < START_WINDOW)
                this.x = 0;
            else this.x = x;
    }

    public int getY() {
        return Y;
    }

    public void render(Graphics2D g) {
                g.setColor(new Color(0xffff0000));//Вынести в константы графики
                g.fillRoundRect(x, Y, WINDTH, HIGHT, 10, 10);
    }
}
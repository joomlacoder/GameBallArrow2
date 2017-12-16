package game;

import display.Graphic;
import graphics.TextureAtlas;
import utils.Time;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements Runnable{

    public static int               WEIDTH              = 1360;
    public static int               HIGHT               = 700;
    public static String            TITLE               = "Balls";
    public static int               CLEAR_COLOR         = 0xff00ffff;
    public static int               NUMBER_BUFFERS      = 3;
    public static final float       UPDATE_RATE         = 60.0f;
    public static final float       UPDATE_INTERVAL     = Time.SECOND/UPDATE_RATE;
    public static final long        IDLE_TIME           = 1;
    public static final String      ATLAS_FILE_NAME     = "textur_atlas.png";
    private static final Ball       START_BALL          = new Ball(0, 400, 50, 3, 0);
    private static final int        MIN_BALL_D          = 5;

    private boolean running;
    private Thread gameThread;
    private java.awt.Graphics2D graphics;
    private TextureAtlas atlas;
    private static Player player;
    private Arrow arrow;
    private int fps = 0;
    private Render renderTread;
    private volatile boolean render;
    //private Level level;

    private List <Ball> balls = new CopyOnWriteArrayList<Ball>();
    private List<Entity> entitys = new CopyOnWriteArrayList<>();

    public Game(){
        running = false;
        Graphic.create(TITLE, WEIDTH, HIGHT, CLEAR_COLOR, NUMBER_BUFFERS, false);
        graphics = Graphic.getGraphics();
        Graphic.addInputListener(getKeyListener());
        //atlas = new TextureAtlas(ATLAS_FILE_NAME);
        player = new Player();
        entitys.add(player);
        arrow = new Arrow();
        entitys.add(arrow);
        //level = new Level(atlas);
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
        renderTread = new Render();
        renderTread.start();
    }

    public synchronized void stop(){
        if (!running)
            return;
        running = false;
        try {
            gameThread.join();
        }catch (InterruptedException e){
            System.err.println("Join gameThread: ");
            e.printStackTrace();
        }
        cleanUp();
    }

    private void update(){
        for (Ball ball : balls) {
            ball.aplay();
            CollisionBall(ball);
        }
        player.aplay();
        arrow.aplay();;
    }

    private class Render extends Thread {

        @Override
        public void run() {

            while (true) {
                    Graphic.clear();
                    entitys.stream().forEach(x -> x.render(graphics));//.filter()
                    Graphic.swapBuffers();
                    fps++;
            }
        }
    }

    private void render(){

    }

    private void cleanUp(){
        Graphic.destroy();
    }

    private boolean isCollisionBall(Ball ball, String parm){

        int ballX = ball.getX();
        int ballY = ball.getY();
        int ballXD = ballX + ball.getD();
        int ballYD = ballY + ball.getD();

        if(parm.equals("arow"))
            return ballX < arrow.getX() && arrow.getX() < ballXD
                    && ballY < arrow.getY() + arrow.getH() && arrow.getY() < ballYD;
        else
            return ballX < player.getX() + player.getWINDTH()
                    && player.getX() < ballXD && player.getY() < ballYD;
    }

    private void CollisionBall(Ball ball){
        if(ball.isTurn()) {
            if (isCollisionBall(ball, "arow")) {
                respawn(ball);
                removeBall(ball);
            } else if (isCollisionBall(ball, ""))
                removeBall(ball);
        } else ball.doTurn();
    }

    public void run() {
        fps = 0;
        int upd = 0;
        int updl = 0;
        long count = 0;
        float delta = 0;
        long lastTime = Time.get();

        while (running) {

            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;
            count += elapsedTime;
            delta += (elapsedTime / UPDATE_INTERVAL);

            while (delta > 1) {
                update();
                upd++;
                delta--;
            }

            if (count >= Time.SECOND) {
                Graphic.setTitle(TITLE + " || Fps: " + fps + " | Upd: " + upd  + "| " + entitys.size());
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }
        }
    }

    private void respawn(Ball ball) {
        int BallD = ball.getD()/2;
        if(BallD >= MIN_BALL_D) {
            newBall(ball.getX(), ball.getY(), BallD, 3, ball.getSpeed());
            newBall(ball.getX(), ball.getY(), BallD, -3, ball.getSpeed());
        }
    }

    private Ball newBall(int x, int y, int d, int direction, int speed){
        Ball ball = new Ball(x, y, d, direction, speed);
        balls.add(ball);
        entitys.add(ball);
        return ball;
    }

    private void removeBall(Ball ball){
        balls.remove(ball);
        entitys.remove(ball);
    }

    public KeyListener getKeyListener(){
        return new KeyListener() {
            public void keyPressed(KeyEvent arg0) {
// System.out.println("P="+arg0.getKeyCode());
                switch(arg0.getKeyCode()){
                    case 82:{newBall(10, 10, 100, 3, 0); break;}
                    case 37:{player.left = true; break;}
                    case 39:{player.right = true; break;}
                    case 32:{arrow.fire(player.getX()); break;}
                }}
            public void keyReleased(KeyEvent arg0) {
                switch(arg0.getKeyCode()){
                    case 37:{player.left=false;break;}
                    case 39:{player.right=false;break;}
                }}
            public void keyTyped(KeyEvent arg0) {
//System.out.println("T="+arg0.getKeyChar());
            }
        };
    }
}


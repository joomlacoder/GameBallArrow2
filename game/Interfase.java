package game;

import java.awt.*;

/**
 * Created by Andrej on 16.12.2017.
 */
public class Interfase extends Entity{

    Game game;

    public Interfase(Game game){
        super(0, 0);
        this.game = game;
    }

    @Override
    public void aplay() {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(200, 0, 0));
        double size = 20;
        g.setFont(new Font("Serif", Font.BOLD | Font.LAYOUT_RIGHT_TO_LEFT, 24));
        g.drawString(game.getCountBall() + " ", 10, 20);
    }
}

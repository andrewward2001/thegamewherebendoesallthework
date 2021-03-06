package Game;

import FuncLibraries.GameFunctions;
import FuncLibraries.HitBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static FuncLibraries.GameFunctions.FRAMEHEIGHT;

public class PipeSet {

    private static final int fh = FRAMEHEIGHT;
    private Point topPipeLoc, botPipeLoc;
    private BufferedImage topPipePic, botPipePic;
    private HitBox topPipeHitBox, botPipeHitBox;
    private int speed;
    private boolean unscored;

    public PipeSet(int x){
        unscored = true;
        speed = 4;
        setup(x);
    }

    private void setup(int x){
        setupLoc(x);
        setupPics();
    }

    private void setupLoc(int x){
        int topPipeY = (int)(Math.random()* GameFunctions.FRAMEHEIGHT/2)+200;
        int botPipeY = fh - topPipeY + (int)(Math.random()*20) + 180;
        topPipeLoc = new Point(x, 0 - topPipeY);
        botPipeLoc = new Point(x, botPipeY);
    }

    private void setupPics(){

        try {
            topPipePic = ImageIO.read(new File("res/topPipe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            botPipePic = ImageIO.read(new File("res/botPipe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        topPipeHitBox = new HitBox(new Rectangle(topPipeLoc.x, topPipeLoc.y, topPipePic.getWidth(), topPipePic.getHeight()));
        botPipeHitBox = new HitBox(new Rectangle(botPipeLoc.x, botPipeLoc.y, botPipePic.getWidth(), botPipePic.getHeight()));
    }

    public HitBox getTopPipeHitBox(){
        return topPipeHitBox;
    }

    public HitBox getBotPipeHitBox(){
        return botPipeHitBox;
    }

    public void update(){
        topPipeHitBox.translate(-speed, 0);
        botPipeHitBox.translate(-speed, 0);
        topPipeLoc.translate(-speed, 0);
        botPipeLoc.translate(-speed, 0);

    }

    public void draw(Graphics2D g2){
        g2.drawImage(topPipePic, topPipeLoc.x, topPipeLoc.y, null);
        g2.drawImage(botPipePic, botPipeLoc.x, botPipeLoc.y, null);
    }

    public int getLoc(){
        return topPipeLoc.x;
    }

    public int getWidth(){
        return topPipePic.getWidth();
    }

    public boolean isUnscored() {
        return unscored;
    }

    public void scored(){
        unscored = false;
    }

}

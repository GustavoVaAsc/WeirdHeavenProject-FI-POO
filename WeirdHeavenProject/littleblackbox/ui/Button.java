package littleblackbox.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import littleblackbox.input.MouseInput;

public class Button {
    private BufferedImage mouseOutImg;
    private BufferedImage mouseInImg;

    private boolean mouseIn;
    private Rectangle boundingBox;
    private Action act;

    public Button(BufferedImage mouseOutImg, BufferedImage mouseInImg,int x, int y,Action act){
        this.mouseOutImg = mouseOutImg;
        this.mouseInImg = mouseInImg;
        boundingBox = new Rectangle(x,y,mouseInImg.getWidth(),mouseInImg.getHeight());
        this.act = act;
    }
    
    public void update(){
        if(boundingBox.contains(MouseInput.X,MouseInput.Y)){
            mouseIn = true;
        }else{
            mouseIn = false;
        }

        if(mouseIn && MouseInput.MLB){
            act.doSomething();
        }
    }

    public void draw (Graphics g){
        if(mouseIn){
            g.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
        }else{
            g.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
        }
    }
}

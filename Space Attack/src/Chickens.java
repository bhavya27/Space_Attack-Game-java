import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.event.*;
public class Chickens{
    int i;
    int x=0,y=0,ya=0,xa=0;
   
    start start;
     Rectangle r;
    public Chickens(start start){
        this.start=start;
        try{
        start.bomb=ImageIO.read(new File("bomb.png"));}
        catch(IOException e){}
    }

    public void move(){
       for(i=0;i<start.al.size();i++){
        r=start.al.get(i);
        r.y=r.y+1;
        if(r.y>start.getHeight())
            start.al.remove(r);
        if(r.intersects(start.rocket.getRocketBounds()))
            start.gameOver();
        if(start.rocket.flag){
        if(r.intersects(start.rocket.curr)){
            Sound.Kill.play();
                start.al.remove(r);

                start.score++;
         }}

  } }

    public void paint(Graphics g){
         for(Rectangle r:start.al){
            g.drawImage(start.bomb,r.x,r.y,r.width,r.height,null);
         
      }
    }   
}
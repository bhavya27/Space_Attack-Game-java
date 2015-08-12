import java.awt.event.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Rocket{
    int X=0,Y=411;
    int Xa=0,Ya=0;
    int WIDTH=40;
    int HEIGHT=50;
    int WIDTHB=7,HEIGHTB=25;
    Rectangle r,curr;
  static boolean flag=false;
  static boolean canShoot=true;//so that only single bullet can be shooted at a time.
  start start;
    public Rocket(start start){
        this.start=start;
        try{
        start.tank=ImageIO.read(new File("tank.png"));
        start.bullet=ImageIO.read(new File("bullet.png"));
    }catch(IOException e){e.printStackTrace();}
    }

    
    public void move(){
        if(X+Xa<0) Xa=0;
        if(X+Xa>start.getWidth()-WIDTH)
            Xa=start.getWidth()-WIDTH;
        if(Y+Ya<0) {
            Y=Ya=0;
          }
        if (Y + Ya > start.getHeight() - HEIGHT) {
            Ya = 0;
        }
        if(flag){
         for(int i=0;i<start.Bullets.size();i++){
             canShoot=false;
             curr=start.Bullets.get(i);
            curr.y=curr.y-5;
            
            if((curr.y-25)<0){
                flag=false;
                canShoot=true;
            start.Bullets.remove(curr);
            }
        }
        }
  }
    public void keyPressed(KeyEvent ke){
    switch(ke.getKeyCode()){
        case KeyEvent.VK_RIGHT:Xa=Xa+10;
        break;
        case KeyEvent.VK_LEFT:Xa=Xa-10;
        break;
        case KeyEvent.VK_UP:Ya=Ya-10;
        break;
        case KeyEvent.VK_SPACE: 
            if(canShoot){
            r=new Rectangle((X+2*Xa+30)/2,(Y+Ya)-25,WIDTHB,HEIGHTB);
            start.Bullets.add(r);
            flag=true;
        }
            break;
        case KeyEvent.VK_DOWN:Ya=Ya+10;
        }
    }
   public Rectangle getRocketBounds(){
       return new Rectangle(X+Xa,Y+Ya,WIDTH,HEIGHT);
   }
    public void paint(Graphics g){
          try{
     start.wall=ImageIO.read(new File("wall.gif"));
     }catch(IOException io){io.printStackTrace();}
     int iw=start.wall.getWidth();
     int ih=start.wall.getHeight();
     if(iw>0 && ih>0){
     for(int x=0;x<start.getWidth();x+=iw){
     for(int y=0;y<start.getHeight();y+=ih){
         g.drawImage(start.wall,x,y,iw,ih,null);
     }
     }
     }
      g.drawImage(start.tank,X+Xa,Y+Ya,WIDTH,HEIGHT,null);
        for(Rectangle r:start.Bullets)
            g.drawImage(start.bullet,r.x,r.y,r.width,r.height,null);
       
          }  
}
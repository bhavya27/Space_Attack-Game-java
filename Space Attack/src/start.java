import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.awt.Rectangle;
import java.awt.Font;
import javax.imageio.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
public class start extends JPanel{
int num,density=50;
int score=0;
Rocket rocket=new Rocket(this);
Chickens chick=new Chickens(this);
List<Rectangle> al=new ArrayList<Rectangle>();
List<Rectangle> Bullets=new ArrayList<Rectangle>();
BufferedImage bullet,tank,bomb,back,wall,menu;

public void addRect(Rectangle r){
   al.add(r);
   
}
    public start(){

        addKeyListener(new KeyAdapter(){
         public void keyPressed(KeyEvent e){
            rocket.keyPressed(e);
            }
        });
        setFocusable(true);
        Sound.Battle.loop();
    }
    public void move(){
        rocket.move();
        chick.move();
    }
 public void paint(Graphics g){
   super.paint(g);
   rocket.paint(g);
   chick.paint(g);
   g.setColor(Color.GRAY);
   g.setFont(new Font("Verdana",Font.BOLD,30));
  g.drawString(String.valueOf(score),0,30);
 
 }
 public void gameOver(){
     Sound.Battle.stop();
     Sound.Explosion.play();
    JOptionPane.showMessageDialog(this,"Game Over","Game Over",JOptionPane.YES_NO_OPTION);
     System.exit(ABORT);
 }
public static void main(String[] args) throws Exception{
    int w=30,h=30;
    
    Rectangle rec;
    Random r=new Random();
    JFrame f=new JFrame("Space");
    start pp=new start();
    f.add(pp);
    f.setSize(460,490);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
  
while(true){
   rec=new Rectangle(r.nextInt(pp.getWidth()-w),0,w,h);
   pp.num++;
   if((pp.num) % pp.density==0){
   pp.addRect(rec);
   }
   pp.repaint();
   Thread.sleep(10);
   pp.move();
   if(pp.score>10)
   pp.density=35;
   else if(pp.score>20)
   pp.density=25;
   else if(pp.score>30)
   pp.density=10;
}
}
}

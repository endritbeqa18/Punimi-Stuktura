import javax.swing.*;
import java.awt.*;

public class Output extends JPanel
{private BModel b;
 private int size;
 private int x;
 private int y;

  public Output(BModel b,int frameSize,int size,int x,int y,String title)//krijon nje dritare grafike
  {this.b=b;
   this.size=size;
   this.x=x;
   this.y=y;
   JFrame frame=new JFrame();
   frame.setSize(frameSize,frameSize);
   frame.setVisible(true);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setLocation(0,0);
   frame.setTitle(title);
   frame.setAlwaysOnTop(true);
   frame.getContentPane().add(this);
  
  
  }


   public void paintComponent(Graphics g)
   {gameStand(size,x,y,g);
   }
   



    private void gameStand(int size,int x,int y,Graphics pen)//i ngjyros dy grids
    {  gridStand(b.getGrid1(),size,x,y,pen);
       gridStand(b.getGrid2(),size,x+b.num*size+80,y,pen);
        pen.setColor(Color.black);
       pen.drawString("If you don't want to play anymore type in quit",x,y+num*size+50);
    }
  
  
   private void gridStand(int[][] g,int size,int x,int y,Graphics pen)//e ngjyros 1 grid
   {pen.setColor(Color.black);                      
    pen.fillRect(x,y,b.num*size+2,b.num*size+2);    
      for(int i=0; i<b.num; i++)                  
      { pen.setColor(Color.black);                
        pen.drawString(""+(1+i),x+(int)((1.0/3+i)*size),y-size/3);//vizaton numrat
        pen.drawString(""+(1+i),x-size*2/3,y+(int)((2.0/3+i)*size));
         for(int j=0; j<b.num; j++)
         {
                     
            if(g[i][j]==1 || g[i][j]==4)//nese osht 1 ose 4 nuk ki gjujt aty munet mu kon anije ose uje(bardh)
            {pen.setColor(Color.white);
               pen.fillRect(x+j*size+2,y+i*size+2,size-4,size-4);
            }
            if(g[i][j]==0){pen.setColor(Color.blue);//nese osht 0 ki gjut qaty osht uje(kaltert)
               pen.fillRect(x+j*size+2,y+i*size+2,size-4,size-4);
               pen.setColor(Color.white);}
            
            if(g[i][j]==2 )
            {pen.setColor(Color.red); //nese osht 2 e ki prish at anije(kuq)
               pen.fillRect(x+j*size+2,y+i*size+2,size-4,size-4);
               pen.setColor(Color.white);
            }
            if(g[i][j]==3)//nese osht 3 ja ki qellu po ende se ki prish anijen(gjelbert)
            {pen.setColor(Color.green);
               pen.fillRect(x+j*size+2,y+i*size+2,size-4,size-4);
               pen.setColor(Color.white);
            }
            if(g[i][j]==5)
            {pen.setColor(Color.green);//osht 5 vetem deri sa ti vendos anijet
               pen.fillRect(x+j*size+2,y+i*size+2,size-4,size-4);
               pen.setColor(Color.white);

         }
      }
   
   }
   }
 

 
}

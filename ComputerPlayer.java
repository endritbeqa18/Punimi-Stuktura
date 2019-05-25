public class ComputerPlayer
{private BModel b;
   private int num;
   private int i=10;
   private int j=10;
   private int direction=0;
   private int length=0;
   int times=0;
   int timescon=0;

 
   public ComputerPlayer(BModel model)
   {b=model;
      this.num=model.getNum();
   } 
  
   public boolean continueShot(int[][] g,ShipMaker[] shipTypes)//nese e din ne qfar drejtimi shkon anija vazhdon ne at drejtim
   { ++timescon;
     if (timescon>20){
   timescon=0;
   return false;}
      if(direction!=0){
      if (direction==1 && (j+1)<num){ 
        if(!b.checkShotBounds(i,j+1) && !b.checkShotThere(g,i,j+1)){
                                    g[i][j+1]-=1;
                                    ++length;
         for(int a=0; a<shipTypes.length; a++)
         {shipTypes[a].shot(i,j+1);}
         for(int a=0; a<shipTypes.length; a++)
         {shipTypes[a].destroyShipCheck(g);}
         if(g[i][j+1]==0){direction=0;
              j=j-length;
              length=0;}
         j=j+1;}
         else{direction=3;
              j=j-length;
              length=0;
              continueShot(g,shipTypes);}
         return true;}
      
         
      if (direction==2 && (i-1)>=0){ 
         if(!b.checkShotBounds(i,j+1) && !b.checkShotThere(g,i,j+1)){
                                    g[i-1][j]-=1;
                                    ++length;
         for(int a=0; a<shipTypes.length; a++)
         {shipTypes[a].shot(i-1,j);}
         for(int a=0; a<shipTypes.length; a++)
         {shipTypes[a].destroyShipCheck(g);}
         if(g[i-1][j]==0){direction=0;
              i=i-length;
              length=0;}
         i=i-1;}
         else{direction=0;
              i=i-length;
              length=0;
              continueShot(g,shipTypes);}
         return true;}         
      if (direction==3 && (j-1)>=0){ 
      if(!b.checkShotBounds(i,j-1) && !b.checkShotThere(g,i,j-1)){
                                    g[i][j-1]-=1;
                                    ++length;
         for(int a=0; a<shipTypes.length; a++)
         {shipTypes[a].shot(i,j-1);}
         for(int a=0; a<shipTypes.length; a++)
         {shipTypes[a].destroyShipCheck(g);}
         if(g[i][j-1]==0){direction=0;
              j=j+length;
              length=0;}
         j=j-1;}
         else{direction=0;
              j=j+length;
              length=0;
              continueShot(g,shipTypes);}
         return true;}            
                  
      if (direction==4 && (i+1)<num){
      if(!b.checkShotBounds(i+1,j) && !b.checkShotThere(g,i+1,j)){
                                    g[i+1][j]-=1;
                                    ++length;
         for(int a=0; a<shipTypes.length; a++)
         {shipTypes[a].shot(i+1,j);}
         for(int a=0; a<shipTypes.length; a++)
         {shipTypes[a].destroyShipCheck(g);}
         if(g[i+1][j]==0){direction=0;
              i=i-length;
              length=0;}
         i=i+1;}
         else{direction=0;
              i=i-length;
              length=0;
              continueShot(g,shipTypes);}
         return true;}            
            }                                   
   return false;   
  }
 
  
  
  
   public boolean foundShot(int[][] g,ShipMaker[] shipTypes)//nese ia qellon e shikon ne kater anet dhe vendos ne cilin 
   {++times;                                               //drejtim shkon 
   if (times>20){ i=10;
                  j=10;
                  times=0;
   randomShot(g,shipTypes);
   return false;}
    if(i==10 &&  j==10){return false;}
     else
      {
              int r=(int)((Math.random()*4)+1);
     
         switch (r)
         {
            case 1:
               {
                  if((j+1)<num && !b.checkShotBounds(i,j+1) && !b.checkShotThere(g,i,j+1) )
                  {
                   g[i][j+1]-=1;
                     if(g[i][j+1]==3){direction=1;
                                      j=j+1;
                     for(int a=0; a<shipTypes.length; a++)
                     {shipTypes[a].shot(i,j);
                     shipTypes[a].destroyShipCheck(g);
                     }}
                     break;}
                     else{foundShot(g,shipTypes);}
                     break;
               }
            case 2:
               {
                  if((j-1)>=0 && !b.checkShotBounds(i,j-1) && !b.checkShotThere(g,i,j-1) )
                  {
                  g[i][j-1]-=1;
                     if(g[i][j-1]==3){direction=3;
                                      j=j-1;
                     for(int a=0; a<shipTypes.length; a++)
                     {shipTypes[a].shot(i,j);
                     shipTypes[a].destroyShipCheck(g);
                     }}
                     break;}
                     else{foundShot(g,shipTypes);}
                     break;
               }
            case 3:
               {
                  if((i+1)<num && !b.checkShotBounds(i+1,j) && !b.checkShotThere(g,i+1,j) )
                  {
                  g[i+1][j]-=1;
                     if(g[i+1][j]==3){direction=4;
                                      i=i+1;
                     for(int a=0; a<shipTypes.length; a++)
                     {shipTypes[a].shot(i,j);
                     shipTypes[a].destroyShipCheck(g);
                     }}
                     break;}
                     else{foundShot(g,shipTypes);}
                     break;
               }
            case 4:
               {
                  if((i-1)>=0 && !b.checkShotBounds(i-1,j) && !b.checkShotThere(g,i-1,j) )
                  {
                  g[i-1][j]-=1;
                    
                     if(g[i-1][j]==3){direction=2;
                                      i=i-1;
                     for(int a=0; a<shipTypes.length; a++)
                     {shipTypes[a].shot(i,j);
                     shipTypes[a].destroyShipCheck(g);
                     }}
                     break;}
                     else{foundShot(g,shipTypes);}
                     break;
               }                       
         }
      
      times=0;
     return true; }
   }
      
  
   public void randomShot(int[][] g,ShipMaker[] shipTypes)//cakton koordinatat  me math.random
   {int x=(int)(Math.random()*num);
      int y=(int)(Math.random()*num);
      if(g[x][y]==3 || g[x][y]==0 || g[x][y]==2){randomShot(g,shipTypes);}
      else{g[x][y]-=1;
      if(g[x][y]==3){this.i=x;
                     this.j=y;}
      for(int a=0; a<shipTypes.length; a++)
      {shipTypes[a].shot(x,y);
      shipTypes[a].destroyShipCheck(g);}
      }
      
   } 




   public void computerShot(int[][] g,ShipMaker[] shipTypes)//e shikon ne qfar gjendje eshte loja dhe vazhdon me metoden e
   {if(continueShot(g,shipTypes)){}                        //caktuar
      else{if(foundShot(g,shipTypes)){}
              else{randomShot(g,shipTypes);}}
      
   
   }
}

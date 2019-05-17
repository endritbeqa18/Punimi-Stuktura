public class ShipMaker
{private final int shipLength;
   private int[][][] ships;
   private int shipNum;
   private int num;
   private String name;
 
 
   public ShipMaker(BModel model,int length,int shipNumber,String name)
   {
      num=model.getNum();
      shipLength=length;
      shipNum=shipNumber;
      ships=new int[model.getNum()][model.getNum()][shipNumber];
      this.name=name; 
   }

  public int getShipNum()//kthen numrim e anijeve te ketij lloji
  {return shipNum;}
  
  public String getShipName()
  {return name;}
  
  public boolean checkSetBounds(int i,int j)//e shikon a esht mbrenda fushes
 {if(i<1 || j<1 || i>num || j>num  ){return false;}
  return true;
 }

   public void setShip(int[][] g,int i,int j,int i1,int j1,int shipNumber)
   {int xLength=Math.abs(i-i1)+1;//kur e qet anijen e kqyr a duhet mi qit horzontalisht a vertikalisht 
      int yLength=Math.abs(j-j1)+1;// e pastaj qato koordinata ju shton 4 en arrajin kryesora(ku ruhen krejt anijet)
      for(int a=0; a<shipLength; a++)//e ju shton ka 2 arrajit te asej anijes
      {
         if(xLength==1)
         {
            if(j<j1){g[i][j+a]+=4;
               ships[i][j+a][shipNumber]=2;}
            if(j>j1){g[i][j-a]+=4;
               ships[i][j-a][shipNumber]=2;}}        
         else
         {
            if(i<i1){g[i+a][j]+=4;
               ships[i+a][j][shipNumber]=2;}
            if(i>i1){g[i-a][j]+=4;
               ships[i-a][j][shipNumber]=2;}}
      }
   }
   
   
   public void removeSetShip(int[][] g,int shipNumber)
   {for(int i=0; i<num; i++)//i hek anijet nese lojtarit nuk i pelqejne pozitat
    {for(int j=0; j<num; j++)
        {if(g[i][j]==5){g[i][j]=1;}//arrajit kryesor ku ka 5(kur ti qet anijet pozitat e kan vleren 5) i kthen 1 si ne fillim
          if(ships[i][j][shipNumber]==2){ships[i][j][shipNumber]=0;}//arrajit te anijeve ku ka 2 i bon 0
        }
    }
   }
   
   public boolean setShipRandom(int[][] g,int shipNumber)//kthen false nese nuk e ka qit, true nese e ka qit
   {int i=(int)(Math.random()*num);//i jep kordinatet i,j mes 1 edhe madhsise te arrayit
    int j=(int)(Math.random()*num);
    int r=(int)(Math.random()*4+1);//edhe r per mi caktu drejtimin e anijes 
    switch (r)
    {case 1:{if(checkSetBounds(i,j+shipLength) && checkShipHorizont(g,i,j,j+shipLength))//e kqyr a osht inbounds edhe mes ka anije
             {for(int a=0; a<shipLength; a++)
                {g[i][j+a]+=4;
                 ships[i][j+a][shipNumber]=2;}
                 break;}//nese e ka qit anijen del prej switchit edhe metoda kthen true
                 else{return false;}}//nese ska mujt mi qit metoda kthen false
    case 2:{if(checkSetBounds(i,j-shipLength) && checkShipHorizont(g,i,j,j-shipLength))
             {for(int a=0; a<shipLength; a++)
                {g[i][j-a]+=4;
                 ships[i][j-a][shipNumber]=2;}
                 break;}
                 else{return false;}}
    case 3:{if(checkSetBounds(i+shipLength,j) && checkShipVertical(g,i,j,i+shipLength))
             {for(int a=0; a<shipLength; a++)
                {g[i+a][j]+=4;
                 ships[i+a][j][shipNumber]=2;}
                 break;}
                 else{return false;}}
    case 4:{if(checkSetBounds(i-shipLength,j) && checkShipVertical(g,i,j,i-shipLength))
             {for(int a=0; a<shipLength; a++)
                {g[i-a][j]+=4;
                 ships[i-a][j][shipNumber]=2;}
                 break;}
                 else{return false;}}                       
    }
    return true;
      }

   public boolean checkShipHorizont(int[][] g,int i,int j,int j1)//e kqyr horzontalisht mes ka anije
   {boolean rez=true;
      if(j<j1){//e kqyr nga ana e djatht
         for(int a=0; a<shipLength; a++){rez=rez && checkPlaceShipThere(g,i,j+a);}}
      if(j1<j){//e kqyr nga ana e majte
         for(int a=0; a<shipLength; a++){rez=rez && checkPlaceShipThere(g,i,j-a);}}
      return rez;
   }
 
   public boolean checkShipVertical(int[][] g,int i,int j,int i1)//e kqyr vertikalisht a ka anije
   {boolean rez=true;
      if(i<i1){//e kqyr teposht
         for(int a=0; a<shipLength; a++){rez=rez && checkPlaceShipThere(g,i+a,j);}}
      if(i1<i){//e kqyr tenalt
         for(int a=0; a<shipLength; a++){rez=rez && checkPlaceShipThere(g,i-a,j);}}
      return rez;
   }
 
   public boolean checkShipShape(int[][] g,int i,int j,int i1,int j1)
   {//e kqry a e ka anija formen ne rregull ose gjatesia ose gjeresia osht njo e tjetra osht sa gjatsia e anijes
      if(((i-i1==0 && Math.abs(j-j1)+1==shipLength) || (j-j1==0 && Math.abs(i-i1)+1==shipLength)))
      {
         return true;}
      return false;
   }

   public boolean checkPlaceShipThere(int[][] g,int i,int j)
   {// e kqyr a ka anije en qat pozite e arrajin kryesor
      if(g[i][j]==4 || g[i][j]==5){
         return false;}
      return true;
   }

  public void shot(int i,int j)
 {for(int k=0; k<shipNum; k++)
     {ships[i][j][k]-=1;//ja hek krejt arrajave tcitij llojit anijes ka 1 ne qato koordinata
     }
 }
   

 public boolean checkShipArray(int k)
  {for(int i=0; i<num; i++)//e kqyr mes ka ende pjese te anijes ta pa gjujtne
    {for(int j=0; j<num; j++)
     {
      if(ships[i][j][k]==2){return false;}
     }
    }
   return true;
  }

 public void destroyShipArray(int[][] g,int k)
  {for(int i=0; i<num; i++)
    {for(int j=0; j<num; j++)
     { if(ships[i][j][k]==1){g[i][j]=2;
                        ships[i][j][k]=2;/*ne arrayin kryesor at pozit e bon 2 per mi ngjyros kuq
                        e ne arrayin e anijes aot pozita i kthen ne 2 per mes mi kontrrollu mo pasi qe
                        nuk mundet me gjuajt ne ate pozit */ 
                       }
     }
    } 
  }





public void destroyShipCheck(int[][] g)
{for(int k=0; k<shipNum; k++)//e kqyr edhe nese duhet e prish at anije
    {if(checkShipArray(k)){
      destroyShipArray(g,k); 
    
                                             }}}
                                             
    }
    
    
    
    
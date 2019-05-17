public class BModel
{private int[][] grid1;/*array dy-dimensional ku ruhet pozita e anijeve dhe gjuajtjeve*/ 
 private int[][] grid2;
 private String[] setOption={"Yourself","Random"};
 private String[] setAgain={"Yes","No"};
 public int num;//madhesia e arrayit

 

 public BModel(int num)
 {this.num=num;
  grid1=new int[num][num];
  grid2=new int[num][num];
  for(int i=0; i<num; i++)
  {for(int j=0; j<num; j++)
   {grid1[i][j]=1;
    grid2[i][j]=1;
   }
  }
 }

public int[][] getGrid1()//kthen grid1
 {return grid1;}

public int[][] getGrid2()
 {return grid2;}
 
 public int getNum()//kthen madhesine e grid e cila jipet ne konstruktor
 {return num;}
 
 
 public void afterSet(int[][] g)/*kur ti vendos anijet pozitat ngjyrosen me te gjelbert per ti pare ku jan vendosur 
                                  e pastaj kur eshte rradhe per lojtarin tjeter nuk shihen*/
 {for(int i=0; i<num; i++)
  {for(int j=0; j<num; j++)
   {if(g[i][j]==5){g[i][j]=4;}
   }
  }
 }



 
 public boolean checkShotBounds(int i,int j)//e shikon nese eshte mbrenda fushes gjuajtja
 {if(i<0 || j<0 || i>=num || j>=num ){return true;}
  return false;
 }
 
 public boolean checkShotThere(int[][] g,int i,int j)//shikon nese ke gjuajt ne at pozit
 {if((g[i][j]==0)|| (g[i][j]==3) || (g[i][j]==2) ){return true;}
  return false;
 }

 
  public boolean checkSetBounds(int i,int j)//shikon a eshte mbrenda fushes pozita e anijes
 {if(i<0 || j<0 || i>=num || j>=num  ){return false;}
  return true;
 }


 
 public void shot(int[][] g,Input in,int i,int j,ShipMaker[] shipTypes)
 { if(checkShotBounds(i,j))//e shikon a i ke jap koordinatat mir nese jo thirr apet metoden e provon perseri
     {in.invalid("Shot is out of bounds");
      String s=in.getCoordinates("Coordinates of the next shot  (XX YY)");
      shot(g,in,in.getx(s),in.gety(s),shipTypes);}
   else{if(checkShotThere(g,i,j))//kqyr a ki gjujt qaty nese jo provo perseri e thirr apet qit metod
          {in.invalid("Already shot there Captain");
           String s=in.getCoordinates("Coordinates of the next shot  (XX YY)");
           shot(g,in,in.getx(s),in.gety(s),shipTypes);}
              else{ g[i][j]-=1;//nese eshte gjuajtja mir ja hek qasej pozite 1 dhe e bon 3 del e gjelbert
       for(int a=0; a<shipTypes.length; a++)
       {shipTypes[a].shot(i,j);//hek vleres ne ket pozite nga 1 ne arrajit tseciles anije
       shipTypes[a].destroyShipCheck(g);//e shikon nese e ka prish anijen per mi ngjyros kuq 
     }}}}

 public boolean gameEnd(int[][] g)
 {for(int i=0; i<num; i++)
  {for(int j=0; j<num; j++)
   {if(g[i][j]==4)return false;//kqyr nese ka vlere 4 ne array(anije qe ende nuk eshte qelluar)nese ska maron loja
   }
  }
  return true;
 }
 
 private void remove_ONE_grid(Input i,Output o,int[][] g,ShipMaker[] shipTypes)
 {for (int a=0; a<shipTypes.length; a++) //kur e vendos anijet po don mi ndrru pozitat i hek vlerat e anijeve 
   {for(int c=0; c<shipTypes[a].getShipNum(); c++)
   {shipTypes[a].removeSetShip(g,c);//edhe per array tseciles anije
   }}
  }

 private void set_ONE_grid(Input i,Output o,int[][] g,ShipMaker[] shipTypes)
  {for (int a=0; a<shipTypes.length; a++)//i qet anijet en ni grid prej lojtarit
   {for(int c=0; c<shipTypes[a].getShipNum(); c++)
   {String s1=i.getCoordinates("Where does the "+shipTypes[a].getShipName()+" start");
    int x=i.getx(s1);
    int y=i.gety(s1);
    if(!checkSetBounds(x,y))//shikon a jon koordinatat ne rreggull nese jo e zvoglon count tciklin per
    {i.invalid("Coordinates are out of bounds");//1 per mes mu numru qiky rast edhe vazhdon ciklin me continue
    c--;
    continue;}
    String s2=i.getCoordinates("Where does the "+shipTypes[a].getShipName()+" end");//e njejta per koordinaten mbaruese
    int x2=i.getx(s2);
    int y2=i.gety(s2);
    if(!checkSetBounds(x2,y2))
    {i.invalid("Coordinates are out of bounds");
    c--;
    continue;}
    if(! (shipTypes[a].checkShipShape(g,x,y,x2,y2)) )//e kqyr formen e anijes
    {i.invalid("Thats not a "+shipTypes[a].getShipName());
    c--;//nese nuk osht ne regulle zvoglon count e ciklit per mes mu numru qiky rast e vazhdon
    continue;}
    if(x-x2==0){if(!shipTypes[a].checkShipHorizont(g,x,y,y2))//kqyr mes ka viqse anije ne drejtimin horizontalisht 
    {i.invalid("A "+shipTypes[a].getShipName()+" is already there");
    c--;
    continue;}}
     if(y-y2==0){if(!shipTypes[a].checkShipVertical(g,x,y,x2))//kqyr a ka viqse anije vertikalisht
    {i.invalid("A "+shipTypes[a].getShipName()+" is already there");
    c--;
    continue;}}
    shipTypes[a].setShip(g,x,y,x2,y2,c);//vendos koordinatat ne array tqasaj anije
    o.repaint();   }}
   
   o.repaint();
   int p1=i.option(setAgain,"Would you like to set your ships again?");//e pyt lojtarin a don mi vendos prap anijet
   if(p1==0){remove_ONE_grid(i,o,g,shipTypes);//i hek anijet nese po edhe e thirr prap metoden
    set_ONE_grid(i,o,g,shipTypes);}
   else{}

  
   afterSet(g);
   o.repaint();
  }
  
  private void set_ONE_grid_random(Input i,Output o,int[][] g,ShipMaker[] shipTypes)//i vendos anijet en ni array random
  {for (int a=0; a<shipTypes.length; a++) 
   {for(int c=0; c<shipTypes[a].getShipNum(); c++)
   {if(shipTypes[a].setShipRandom(g,c)){}//nese ka qen e sukseshme vendosja e anijes vazhdon me anijen tjeter
          else{c--;//nese nuk eshte e sukseshme ather nuk ndryshon asgje ne array nuk numrohet ky rast edhe vazhdon cikli
           continue;}}
   }
   o.repaint();
   int p1=i.option(setAgain,"Would you like to set your ships again?");
   if(p1==0){remove_ONE_grid(i,o,g,shipTypes);//e pyt lojarin a don edhe niher mi qit nese po i hek anijet
    set_ONE_grid_random(i,o,g,shipTypes);}//e thirr prap metoden e i qet apet
   else{}//nese jo vazhdon programi
   afterSet(g);
   o.repaint();
  }

  private void set_ONE_grid_Computer(Input i,Output o,int[][] g,ShipMaker[] shipTypes)
  {for (int a=0; a<shipTypes.length; a++)//komjuteri kur ti qet anijet i qet ne menyre  random po nuk e thirr  
   {for(int c=0; c<shipTypes[a].getShipNum(); c++)// repaint per mes mi pa lojtari ku jon
   {if(shipTypes[a].setShipRandom(g,c)){}
       else{c--;
           continue;}}
   }
   afterSet(g);
   o.repaint();
  }
  
  
  
  public void setGrids(Input i,Output o,ShipMaker[] shipTypes1,ShipMaker[] shipTypes2)
  {int p1=i.option(setOption,"Player 1 how would you like to set your ships");
   if(p1==0){set_ONE_grid(i,o,grid1,shipTypes1);}//e pyt lojtarin si pe don mi vendos anijet(vet a random )
   else{set_ONE_grid_random(i,o,grid1,shipTypes1);}//cilen ta zhgjedh at metod e thirr 
   
   int p2=i.option(setOption,"Player 2 how would you like to set your ships");//njejta per lojtarin 2
   if(p2==0){set_ONE_grid(i,o,grid2,shipTypes2);}
   else{set_ONE_grid_random(i,o,grid2,shipTypes2);}
  }
  
   public void setGridsComputer(Input i,Output o,ShipMaker[] shipTypes1,ShipMaker[] shipTypes2)
  {int p1=i.option(setOption,"Player how would you like to set your ships");
   if(p1==0){set_ONE_grid(i,o,grid1,shipTypes1);}//e pyt lojtarin si pe don mi vendos anijet 
   else{set_ONE_grid_random(i,o,grid1,shipTypes1);}
   
   set_ONE_grid_Computer(i,o,grid2,shipTypes2);//e kompjuteri i qet vet me metoden computer
  }
 }

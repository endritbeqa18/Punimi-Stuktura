public class Controller
{

 private BModel b=new BModel(10);
 private Input i=new Input();
 private Output o=new Output(b,600,20,50,50,"Battleship");
 private ComputerPlayer c=new ComputerPlayer(b);
 private String[] options={"1v1","Computer"};
 private String[] playAgain={"Yes","No"};
 /*duhet secili lojtar mi pas ka ni array me anije se perndryshe gjuajtjet e dy lojtareve ruhen en njejten array e 
 munet dyt bashk mi prish ni anije*/

 ShipMaker battleship1 = new ShipMaker(b,4,1,"Battleship");/*Ndertojme tipe te anijeve ku ne konstruktor 
                                                             japim madhesin numrin dhe emrin e anijeve */
 ShipMaker pt1 = new ShipMaker(b,2,1,"Pt");
 ShipMaker carrier1 = new ShipMaker(b,5,1,"Carrier");
 ShipMaker sub1= new ShipMaker(b,3,1,"Submarine");
 ShipMaker destroyer1= new ShipMaker(b,3,1,"Destroyer");
 ShipMaker[] shipTypes1=new ShipMaker[]{carrier1,battleship1,sub1,destroyer1,pt1};
 
 ShipMaker battleship2 = new ShipMaker(b,4,1,"Battleship");
 ShipMaker pt2 = new ShipMaker(b,2,1,"Pt");
 ShipMaker carrier2 = new ShipMaker(b,5,1,"Carrier");
 ShipMaker sub2= new ShipMaker(b,3,1,"Submarine");
 ShipMaker destroyer2= new ShipMaker(b,3,1,"Destroyer");
 ShipMaker[] shipTypes2=new ShipMaker[]{carrier2,battleship2,sub2,destroyer2,pt2};
 
 
 public void playgame()//e qet ni dritare mi pyet si don me lujt 1v1 ose kompjuter dhe si te vendos e thirr metoden
 {int choice=i.option(options,"How would you like to play");
  if(choice==0){playgame1v1();}
  if(choice==1){playgameComputer();}
 }
 
 
 public void playgame1v1()
 {  boolean a=true;
  b.setGrids(i,o,shipTypes1,shipTypes2);//i vendos anijet secili lojtar
  o.repaint();
   while(a)//perderisa ka ende anije ne dy arrays cikli vazhdon
   {String s2=i.getCoordinates("Player1 coordinates of the next shot  (XX YY)");
    b.shot(b.getGrid2(),i,i.getx(s2),i.gety(s2),shipTypes2);//i merr koordinatat per gjuajtje
    o.repaint();
                                     
    if(b.gameEnd(b.getGrid2())){a=false;//shikon nese ka fitu nese po e ndal ciklin me break
                           break;}
      o.repaint();
    
   String s1=i.getCoordinates("Player2 coordinates of the next shot  (XX YY)"); 
    b.shot(b.getGrid1(),i,i.getx(s1),i.gety(s1),shipTypes1);//lojtari i dyt gjun
    o.repaint();
       
    if(b.gameEnd(b.getGrid1())){a=false;//kqyr nese ka fitu lojtari i dyt
                           break;}
      o.repaint();
    } 
    o.repaint(); 
    if(i.option(playAgain,"Would you like to play again")==0){new Controller().playgame();}//e pyt a don me lujt prap                    
    else{System.exit(0);}  
 }
   
   
   public void playgameComputer()//metoda per loj kunder kompjuterit
 {boolean a=true;
   b.setGridsComputer(i,o,shipTypes1,shipTypes2);//i vendos anijet lojtari e kompjuteri i vendos pa e bo repaint 
   o.repaint();
   while(a)
   {String s2=i.getCoordinates("Coordinates of the next shot  (XX YY)");
    b.shot(b.getGrid2(),i,i.getx(s2),i.gety(s2),shipTypes2);//lojtari gjuan
    o.repaint();
                                     
    if(b.gameEnd(b.getGrid2())){a=false;//shikon a ka fitu lojtari
                           break;}
      o.repaint();
       
       c.computerShot(b.getGrid1(),shipTypes1);//gjuan kompjuteri    
       o.repaint();
          
    if(b.gameEnd(b.getGrid1())){a=false;//shikon a ka fitu kompjuteri
                           break;}
      o.repaint();
    } 
    o.repaint(); 
    if(i.option(playAgain,"Would you like to play again")==0){new Controller().playgame();}//e pyt a don me lujt prap            
    else{System.exit(0);}  
 }
   
}



import javax.swing.JOptionPane;

public class Input
{JOptionPane j=new JOptionPane(); 

 
 public String getCoordinates(String s)//qel nje dritare te re dhe merr informata
 {String rez=j.showInputDialog(s);
  if(rez==null || rez.length()<3 || rez.trim().length()>5){invalid("Input error");
                                                           return getCoordinates(s);}
  if (rez.toLowerCase().equals("quit")){System.exit(0);}//nese nuk don me lujt shkruan quit
  if(!rez.matches("[0-9][0-9 ]*$") ){invalid("Input error");//pranon vetem numra dhe hapsira
                                                           return getCoordinates(s);}
  else{ rez=rez.trim();   
  return rez;}
 }    
 
 public int getx(String s)
 {String s1=s.trim();
  return new Integer(s1.substring(0,2).trim()).intValue()-1;}//merr dy numrat e par
 
 public int gety(String s)
 {String s1=s.trim();
  return new Integer(s1.substring(s1.length()-2).trim()).intValue()-1;}//merr dy numrat e fundit
  
 
 public int option(String[] s,String s1)//nje dritare me opsione per te caktuar si vazhdon programi
 {String[] options=s;
  int i=j.showOptionDialog(null,s1,"Input",
                j.DEFAULT_OPTION,j.INFORMATION_MESSAGE, null, options, options[1]);
                return i;}
  
 
 
 public void  invalid(String s)//nje dritare per mesazhe qe eshte bere nje gabim
 {j.showMessageDialog(null,s);
 }

}

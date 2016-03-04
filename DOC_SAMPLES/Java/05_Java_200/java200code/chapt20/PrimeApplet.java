import javax.swing.*;
import java.awt.*;
public class PrimeApplet extends JApplet{
	public void init() {
		System.out.println("init--");
	}
	public void paint(Graphics g){
		super.paint(g);
		System.out.println("paint--");
		for(int j=2;j<10;j++){
			g.drawString(showPrim(j),10,j*20);
		}
	}
	public void start() {
		System.out.println("start--");
	}
	public  String showPrim(int j){
		boolean isP=true;
		String str="";
		for(int i=2;i<j;i++){
			if(j%i==0){
				isP=false;
				break;
			}
		}
		if(isP){  str=j+" is Prime!";        }
		else{     str=j+" is Not Prime!";    }
		return str;
	}//
}

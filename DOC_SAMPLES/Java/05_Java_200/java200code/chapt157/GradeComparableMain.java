import java.util.*;
public class GradeComparableMain {
	public static void main(String[] args) {
		Grade[] g= new Grade[7];
		g[0] = new Grade(60,60,70);
		g[1] = new Grade(89,65,67);
		g[2] = new Grade(60,80,100);
		g[3] = new Grade(70,99,80);
		g[4] = new Grade(60,70,80);
		g[5] = new Grade(60,80,80);
		g[6] = new Grade(73,70,80);
		Grade [] g2=GradeBubbleSort.bubble(g);
		System.out.println("입력순 출력==============");
		prints(g2);
	}
	public static void prints(Grade[ ] v){
		int num=v.length;
		for(int i=0;i<num;i++){
		    System.out.println(v[i]);
	   }//for
	   System.out.println();
	}//prints
}

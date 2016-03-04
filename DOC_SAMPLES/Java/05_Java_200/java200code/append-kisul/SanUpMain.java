import java.util.Vector;
import java.util.Arrays;
public class SanUpMain {

	public static void main(String[] args) {
		
		KisulFileReader kr=new KisulFileReader();
		if(kr.isExist("abc3031.txt")){
			kr.readTexts("abc3031.txt");
			/*
			String str=kr.getText(0);
			String [] strs=kr.splist(str);
			for(int i=0;i<strs.length;i++){
				System.out.println(strs[i]);
			}
			*/
			int num=kr.listSize();
			System.out.println("데이타 개수: "+num);
			//for(int i=0;i<num;i++){
				//System.out.println(kr.getText(i));
			//}
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			ProductList plist=new ProductList();
			Vector v=plist.getAllProducts(kr.getAllLines());
			//for(int i=0;i<v.size();i++){
			//	System.out.println(v.elementAt(i).toString());
			//}
			String [] acc={"A","C"};
			Vector v3=plist.getAllProdInGrade(acc,v);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			/*
			for(int i=0;i<v3.size();i++){
				System.out.println((Product)v3.elementAt(i));
			}
			*/
			System.out.println("!!!!!!"+v3.size());
			
			ProductComparator lc=ProductComparator.getInstance();
			Object [] pp=v3.toArray();
			Arrays.sort(pp,lc);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println((Product)pp[4]);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			for(int i=0;i<10;i++){
				System.out.println((Product)pp[i]);
			}
			String msgs=((Product)pp[4]).getPMoney()+"   ";
			       msgs+=((Product)pp[4]).getPPoint()+"   ";
			       msgs+=((Product)pp[4]).getProdNum();
			ResultWrite rw=new ResultWrite();
			System.out.println(rw.isExist("Ans1.txt"));
			rw.setTexts("Ans1.txt",msgs);
			String krs=kr.readText("Ans1.txt");
			System.out.println(krs);
		}
	}
}

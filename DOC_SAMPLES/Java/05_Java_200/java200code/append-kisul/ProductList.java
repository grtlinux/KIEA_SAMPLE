import java.util.*;
public class ProductList {
	private int[] catg={6,1,4,3,3,1,1,3,3,3,1};
	public String [] splist(String str){
		String[] ss=new String[catgLength()];
		ss[0]=str.substring(0,catNumTo(catg,0)).trim();
		for(int i=1;i<ss.length;i++){
			ss[i]=str.substring(catNumTo(catg,i-1),
			               catNumTo(catg,i)).trim();
		}
		return ss;
	}
	public Product splistP(String str){
		Product p=new Product();
		String[] ss=splist(str);
		p.setProduct(ss);
		return p;
	}
	public Vector getAllProducts(Vector v){
		Vector list=new Vector(10,10);
		for(int i=0;i<v.size();i++){
			String str=(String)v.elementAt(i);
			Product p=splistP(str);
			list.addElement(p);
		}
		return list;
	}
    public Vector getAllProdInGrade(String []ss1,Vector v){
		Vector list=new Vector(10,10);
		for(int i=0;i<v.size();i++){
			Product p=(Product)v.elementAt(i);
			for(int j=0;j<ss1.length;j++){
				if(p.getPGrade().equals(ss1[j])){
					list.addElement(p);
				}
			}
		}
		return list;
	}
	private int totalLength(){
		int tot=0;
		for(int i=0;i<this.catg.length;i++){
			tot+=this.catg[i];
		}
		return tot;
	}
	private int catgLength(){
		return this.catg.length;
	}
	private int catNumTo(int [] aa,int a){
		int toto=0;
		if(aa.length<a){
			toto=0;
		}else{
			for(int i=0;i<=a;i++){
				toto+=aa[i];
			}
		}
		return toto;
	}
		
}

import java.util.Comparator;

public class ProductComparator implements Comparator {
	private static ProductComparator lc
			=new ProductComparator();
	private  ProductComparator(){} 
	public static ProductComparator getInstance(){
		return lc;
	}
	//Comparator때문에 구현 할 메서드
	public int compare(Object o1, Object o2) {
		Product sg1=(Product)o1;
		Product sg2=(Product)o2;
		int pMoney1=sg1.getPMoney();
		int pMoney2=sg2.getPMoney();
		int pPoint1=sg1.getPPoint();
		int pPoint2=sg2.getPPoint();
		int prodNum1=sg1.getProdNum();
		int prodNum2=sg2.getProdNum();
		if(pMoney1>pMoney2){   //Comparable을 구현안함
			return -1;  //내림차순
		}else if(pMoney1==pMoney1){
			if(pPoint1>pPoint2){
				return -1;//내림차순
			}else if(pPoint1==pPoint2){
				if(prodNum1>prodNum2){
					return 1;//오름차순
				}else if(prodNum1==prodNum2){
					return 0;//프라이머리키니깐 이런 일은 없을 것
				}else{
					return -1;//내림차순
				}
			}else{
				return 1;//오름차순
			}
		}else {
			return 1;//오름차순
		}
	}
}

/**
 * Created on 2003. 8. 25.<br>
 * 코멘트 사용법 예제<br>
 * javac -use HelloComment.java
 * @author hyonny
 */
public class HelloComment {
	/**
	 * 솟수인가 아닌가를 판별하는 메서드<br>
	 * <p>
	 * 솟수라는 것은 1과 자기 자신을 제외한 어떠한 수로도 
	 * 나누어 떨어뜨릴 수 없는것 
	 * </p>
	 * 판별하려고 하는 <code>int</code>를 입력한다.<br>
	 * <pre>
	 * public boolean isPri(int a){
	 * 	  boolean isP=true ;
	 * 	  int b=(int)Math.sqrt(a);//50이면 2부터 7까지 
	 * 	  for(int i=2;i<=b;i++){
	 * 		if(a%i==0){   //(50,2) (50,3) (50,4) .....
	 * 			isP=false;
	 * 			break;    //for탈출
	 * 		}else isP=true;
	 * 	  }
	 * 	  return isP;
	 * }
	 * </pre>
	 * @param a 솟수인가를 판별하려고 하는 수<code>int</code>
	 * @return 솟수여부를 리턴 <code>boolean</code>
	 */
	public boolean isPri(int a){
	  boolean isP=true ;
	  int b=(int)Math.sqrt(a); //50이면 2부터 7까지 
	  for(int i=2;i<=b;i++){
		if(a%i==0){      //(50,2) (50,3) (50,4) .....
			isP=false;
			break;       //for탈출
		}else isP=true;
	  }
	  return isP;
	 }
}

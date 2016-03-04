import java.io.*;
public class LottoVO implements Serializable{
//회차 추첨일 1등 당첨번호 보너스 번호대분류 합계 홀짝 끝수 
	private int count;
	private String day;
	private int num1;
	private int num2;
	private int num3;
	private int num4;
	private int num5;
	private int num6;
	private int num7;
	//private String numParts;
	//private int sum;
	//private String evenOdd;
	
	public String toString(){
		//String s="회차/추첨일/ 1등:::::::당첨번호/보너스/번호대분류/합계/홀짝 끝수";
		//String s="회차/추첨일/ 1등:::::::당첨번호/보너스";
		String s="";
		s+=this.getCount()+"/";
		s+=this.getDay()+"/{";
		s+=this.getNum1()+",";
		s+=this.getNum2()+",";
		s+=this.getNum3()+",";
		s+=this.getNum4()+",";
		s+=this.getNum5()+",";
		s+=this.getNum6()+"}/";
		s+=this.getNum7()+"/";
		s+="\n";
		//s+=this.getNumParts()+"/";
		//s+=this.getSum()+"/";
		//s+=this.getEvenOdd();
		return s;
	}

/**
 * @return
 */
public int getCount() {
	return count;
}

	/**
	 * @return
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @return
	 
	public String getEvenOdd() {
		return evenOdd;
	}
*/
	/**
	 * @return
	 */
	public int getNum1() {
		return num1;
	}

	/**
	 * @return
	 */
	public int getNum2() {
		return num2;
	}

	/**
	 * @return
	 */
	public int getNum3() {
		return num3;
	}

	/**
	 * @return
	 */
	public int getNum4() {
		return num4;
	}

	/**
	 * @return
	 */
	public int getNum5() {
		return num5;
	}

	/**
	 * @return
	 */
	public int getNum6() {
		return num6;
	}

	/**
	 * @return
	 */
	public int getNum7() {
		return num7;
	}

	/**
	 * @return
	 
	public String getNumParts() {
		return numParts;
	}


	public int getSum() {
		return sum;
	}
	*/
/**
 * @param i
 */
	public void setCount(int i) {
		count = i;
	}

	/**
	 * @param string
	 */
	public void setDay(String string) {
		day = string;
	}

	/**
	 * @param string
	
	public void setEvenOdd(String string) {
		evenOdd = string;
	}
 */
	/**
	 * @param i
	 */
	public void setNum1(int i) {
		num1 = i;
	}

	/**
	 * @param i
	 */
	public void setNum2(int i) {
		num2 = i;
	}

	/**
	 * @param i
	 */
	public void setNum3(int i) {
		num3 = i;
	}

	/**
	 * @param i
	 */
	public void setNum4(int i) {
		num4 = i;
	}

	/**
	 * @param i
	 */
	public void setNum5(int i) {
		num5 = i;
	}

	/**
	 * @param i
	 */
	public void setNum6(int i) {
		num6 = i;
	}

	/**
	 * @param i
	 */
	public void setNum7(int i) {
		num7 = i;
	}

	/**
	 * @param string
	 
	public void setNumParts(String string) {
		numParts = string;
	}
*/
	/**
	 * @param i
	
	public void setSum(int i) {
		sum = i;
	}
	 */
	
}

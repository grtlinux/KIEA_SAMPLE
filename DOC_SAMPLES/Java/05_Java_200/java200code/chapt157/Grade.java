public class Grade implements Comparable{
	private double kor;
	private double eng;
	private double math;
	private double total;
	private double mean;
	public Grade(){  this(0,0,0);  }
	public Grade(double kor,double eng,double math){
		this.kor=kor;     this.eng=eng;        this.math=math;
		getMean();
	}
	public String toString (){
		return (kor+"/"+eng+"/"+math+"/"+total+"/"+mean);
	}
	public double getEng() {   return eng;   }
	public double getKor() {   return kor;   }
	public double getMath(){   return math;  }
	public double getMean() {
		mean=getTotal()/3.0;
		return mean;
	}
	public double getTotal() {
		total=(this.kor+this.eng+this.math);
		return total;
	}
	public void setEng(double d) {  eng = d;   getMean();}
	public void setKor(double d) {  kor = d;   getMean();}
	public void setMath(double d){  math = d;  getMean();}
	//Comparable의 구현해야 할 메서드
	public int compareTo(Object o1){
		Grade g2=(Grade)o1;
		double mean1=this.mean;
		double mean2=g2.getMean();
		if(mean1>mean2){
			return -1;  //decreasing
		}else if(mean1==mean2){
			return 0;
		}else {
			return 1;  //increasing
		}
	}
}
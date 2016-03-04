import java.io.*;

public class Product implements Serializable{
	
	private int      prodNum;  //제조번호 숫자 6  x
	private String   prodPart; //제조구분 문자 1
	private String   pName;   //담당자  문자 4
	private int      pValue;  //단가  숫자 3
	private int      pAmount;// 판매랑 숫자 3
	private String   pPant;    // 공장코드 문자 1
	private String   pConsum;    // 거래코드 문자 1
	private int      pPoint;// 제조포인트 숫자 3  xx
	private int      pCount;// 출고 횟수 숫자 3
	private int      pMoney;// 매출액 숫자 3      xxx
	private String   pGrade;    // 제조등급 문자 1
	
	/**
	 * @return
	 */
	public int getPAmount() {
		return pAmount;
	}

	/**
	 * @return
	 */
	public String getPConsum() {
		return pConsum;
	}

	/**
	 * @return
	 */
	public int getPCount() {
		return pCount;
	}

	/**
	 * @return
	 */
	public String getPGrade() {
		return pGrade;
	}

	/**
	 * @return
	 */
	public int getPMoney() {
		return pMoney;
	}

	/**
	 * @return
	 */
	public String getPName() {
		return pName;
	}

	/**
	 * @return
	 */
	public String getPPant() {
		return pPant;
	}

	/**
	 * @return
	 */
	public int getPPoint() {
		return pPoint;
	}

	/**
	 * @return
	 */
	public int getProdNum() {
		return prodNum;
	}

	/**
	 * @return
	 */
	public String getProdPart() {
		return prodPart;
	}

	/**
	 * @return
	 */
	public int getPValue() {
		return pValue;
	}

	/**
	 * @param i
	 */
	public void setPAmount(int i) {
		pAmount = i;
	}

	/**
	 * @param string
	 */
	public void setPConsum(String string) {
		pConsum = string;
	}

	/**
	 * @param i
	 */
	public void setPCount(int i) {
		pCount = i;
	}

	/**
	 * @param string
	 */
	public void setPGrade(String string) {
		pGrade = string;
	}

	/**
	 * @param i
	 */
	public void setPMoney(int i) {
		pMoney = i;
	}

	/**
	 * @param string
	 */
	public void setPName(String string) {
		pName = string;
	}

	/**
	 * @param string
	 */
	public void setPPant(String string) {
		pPant = string;
	}

	/**
	 * @param i
	 */
	public void setPPoint(int i) {
		pPoint = i;
	}

	/**
	 * @param i
	 */
	public void setProdNum(int i) {
		prodNum = i;
	}

	/**
	 * @param string
	 */
	public void setProdPart(String string) {
		prodPart = string;
	}

	/**
	 * @param i
	 */
	public void setPValue(int i) {
		pValue = i;
	}
	public void setProduct(String[] s){
		this.setProdNum(Integer.parseInt(s[0].trim()));
		this.setProdPart(s[1].trim());
		this.setPName(s[2].trim());
		this.setPValue(Integer.parseInt(s[3].trim()));
		this.setPAmount(Integer.parseInt(s[4].trim()));
		this.setPPant(s[5].trim());
		this.setPConsum(s[6].trim());
		this.setPPoint(Integer.parseInt(s[7].trim()));
		this.setPCount(Integer.parseInt(s[8].trim()));
		this.setPMoney(Integer.parseInt(s[9].trim()));
		this.setPGrade(s[10].trim());
	}
	/*
	private int      prodNum;  //제조번호 숫자 6
	private String prodPart; //제조구분 문자 1
	private String pName;   //담당자  문자 4
	private int      pValue;  //단가  숫자 3
	private int      pAmount;// 판매랑 숫자 3
	private String pPant;    // 공장코드 문자 1
	private String pConsum;    // 거래코드 문자 1
	private int      pPoint;// 제조포인트 숫자 3
	private int      pCount;// 출고 횟수 숫자 3
	private int      pMoney;// 매출액 숫자 3
	private String pGrade;    // 제조등급 문자 1
	 */
	public String toString(){
		StringBuffer bf=new StringBuffer();
		bf.append("========================\n")
		.append(this.prodNum+"/")
		.append(this.prodPart+"/")
		.append(this.pName+"/")
		.append(this.pValue+"/")
		.append(this.pAmount+"/")
		.append(this.pPant+"/")
		.append(this.pPoint+"/")
		.append(this.pCount+"/")
		.append(this.pMoney+"/")
		.append(this.pGrade+"\n")
		.append("========================");
		return bf.toString();
	}
}

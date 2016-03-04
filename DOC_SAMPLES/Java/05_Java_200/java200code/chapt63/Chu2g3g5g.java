/*
 * 2g, 3g, 5g의 추가 각각 10개씩 있다. 
 * 이 추들의 조합으로 81g을 만들려고 한다. 
 */
public class Chu2g3g5g {

	public static void main(String[] args) {

		for(int five=0;five<11;five++){
			for(int three=0;three<11;three++){
				for(int two=0;two<11;two++){
				  if(5*five+3*three+2*two==81){
					//String s="5g "+five+"개 3g "+three+"개 2g "+two+"개";
				  	//System.out.println(s);
				  	String str=String.format("5g: %d개, 3g: %d개, 2g: %d개%n",
				  			five,three,two);
				  	System.out.printf(str);
				  }
				}
			}
		}
		System.out.printf("-------------------------%n");
		printCondition(56);
	}
	public static void printCondition(int n){
		for(int five=0;five<11;five++){
			for(int three=0;three<11;three++){
				for(int two=0;two<11;two++){
				  if(5*five+3*three+2*two==n){
					//String s="5g "+five+"개 3g "+three+"개 2g "+two+"개";
				  	//System.out.println(s);
				  	String str=String.format("5g: %d개, 3g: %d개, 2g: %d개%n",
				  			five,three,two);
				  	System.out.printf(str);
				  }
				}
			}
		}
		System.out.printf("-------------------------%n");
	}
}

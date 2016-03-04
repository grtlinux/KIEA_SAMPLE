 public class VariableArguments {
 
 	public static void main(String[] args) {
 		print(3,4,5,6,7);
 		int [] aa={5,4,7,1,9,12,0,3,2,6};
 		print(aa);
 		int a=4;  //배열이 아닌데도 가능
 		print(a);
 		String [ ] sNames={"소철","무궁화","개나리","철쭉"};
 		print(sNames);
 		print("동백");//prinf 원리
 		Flower flo=Flower.LILY;
 		print(flo);
 		print(Flower.values());
 		String s="Welcome to the Java Programming";
 		//String [] ss=s.split(" ");//String[] 리턴 
 		print(s.split(" "));
 	}
 	public static void print(int ... p){
 		for(int en : p){
 			System.out.printf("[%d] ",en);
 		}
 		System.out.printf("%n");
 	}
 	public static void print(String ... p){
 		for(String en : p){
 			System.out.printf("[%s] ",en);
 		}
 		System.out.printf("%n");
 	}
 	public static void print(Flower ... p){
 		for(Flower en : p){
 			System.out.printf("[%s] ",en);//en.toString()-->en.name()
 		}
 		System.out.printf("%n");
 	}
}

public class BitShift2To10Main {
	public static void main(String[] args) {
		BitShift2To10 bit2=new BitShift2To10();
		System.out.println(bit2.binTo10("111100"));
		BitShift10To2 bit=new BitShift10To2();
		bit.shift(-35);
		System.out.println(bit.showsb());
		System.out.println(bit2.binTo10(bit.showsb()));
	}
}

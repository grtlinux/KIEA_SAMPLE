public class BitShift10To2Main {
	public static void main(String[] args) {
		BitShift10To2 bit=new BitShift10To2();
		bit.shift(Integer.MAX_VALUE);
		System.out.println(bit.showsb());
		bit.shift(350);
		System.out.println(bit.showsb());
		bit.shift(-350);
		System.out.println(bit.showsb());
	}
}

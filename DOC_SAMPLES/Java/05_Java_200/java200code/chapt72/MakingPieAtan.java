public class MakingPieAtan {
	//periphery pie
	public static void main(String[] args) {
		System.out.println(Math.PI);
		//System.out.printf("%20.18f%n",Math.PI);
		System.out.println(nakayamaPie());
		System.out.println(gaussPie());
		System.out.println(dassePie());
	}
	public static double nakayamaPie(){
		return 32*Math.atan(1./10)
			-4*Math.atan(1./239)-16*Math.atan(1./515);
	}
	public static double gaussPie(){
		return 12*Math.atan(1./4)+4*Math.atan(1./20)
			+4*Math.atan(1./1985);	
	}
	public static double dassePie(){
		return 4*Math.atan(1./2)+4*Math.atan(1./5)
			+4*Math.atan(1./8);	
	}
}

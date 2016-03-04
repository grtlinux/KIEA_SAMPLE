import java.util.*;
//serialVersionUID
public class Carbarn2 <E extends Car> extends Vector<E>{
	private static final long serialVersionUID=122454214537L;//JAVA5
	public boolean add(E element){
		return super.add(element);
	}
	public E get(int index){
		return super.get(index);
	}
	public void shuffle(){
		Collections.shuffle(this);
	}
	public Vector<E> getAllCars(){
		return this;
	}
}

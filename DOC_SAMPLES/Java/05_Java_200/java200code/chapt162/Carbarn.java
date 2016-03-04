import java.util.*;
public class Carbarn <E extends Car>{
	private Vector<E> barn=new Vector<E>(5, 5);
	public void add(E element){
		barn.add(element);
	}
	public E get(int index){
		return barn.get(index);
	}
	public void shuffle(){
		Collections.shuffle(barn);
	}
	public Vector<E> getAllCars(){
		return barn;
	}
}

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import java.lang.RuntimeException;
import java.util.Arrays;

public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess {
  
  protected Object[] data;
  protected int size;

  public int size() {
   	return size; 
  }
  
  private void rangeCheck(int index) {
   	 if (index < 0 || index >= size)
       throw new IndexOutOfBoundsException("");
  }
  
  @SuppressWarnings("unchecked")
  private E data(int index) {
    return (E) data[index];
  }
  
  private void grow() {
    int newCapacity = data.length*2;
   	data = Arrays.copyOf(data, newCapacity); 
  }
  
  public Vector() {
  	this(10); 
  }
  
  public Vector(int initialCapacity) {
   	data = new Object[initialCapacity];
    size = 0;
  }
  
  public E get(int index) {
    rangeCheck(index);
    return data(index);
  }
  
  public E set(int index, E element) {
    rangeCheck(index);
    E oldValue = data(index);
   	data[index] = element; 
  	return oldValue;
  }
  
  public boolean add(E element) {
   	if (size == data.length) grow(); 
    data[size++] = element;
    return true;
  }
	
  public int indexOf(Object o) {
	// Returns the index of the first occurrence of the specified element 
	// in this list, or -1 if this list does not contain the element. 
	// For loop to go through each element and check if object equals the element in the array.
	// If it doesn't find a match in the array than, its going to print -1.
	for(int i=0; i<size(); i++) { 										
		if(data[i].equals(o))
		return i;
	  }
	return -1;
  }
	
  public int addAfterIf(Object toAdd, Object elementBefore) {
	// Inserts toAdd after elementBefore, IF elementBefore is located
	// in the second half of the arraylist. If elementBefore is in the
	// first half of the arraylist, add toAdd to the end of the arrayList.
	// Return the index of toAdd
	// First need to grow the array just in case its not big enough to add another element into it.
	// Than after going to for loop to pass by each element and see if element you are searching for is in the array.
	// With in the loop, checking if the element is in the first half or second half by doing a if cases. 
	// If it doesn't find the element in the array than it's going to print back a -1.
	 if (size == data.length) grow();
	 for (int i=0; i<size(); i++) 	{
		if (i<size()/2 && data[i].equals(elementBefore)) {
		data[size++] = toAdd;
		return size()-1;
		 }
		 if (data[i].equals(elementBefore)) {
		 data[i+1] = toAdd;
		  return i+1;									  																	
		 }
	  	}
	 return -1;
  }
  
  public static void main(String[] args) {
  	Vector<Integer> intlist = new Vector<Integer>();
    Vector<String> stringlist = new Vector<String>();
    Vector<Vector<Integer>> intveclist = new Vector<Vector<Integer>>();

	for (Integer i = 1; i < 10; i++) {
		intlist.add(i);
	}
	
	System.out.println(intlist);
	System.out.println(intlist.indexOf(7));
	System.out.println(intlist.indexOf("seven")); 										
	System.out.println(intlist.addAfterIf(3, 1));									// Testing addAfterIf with 3, 1 as the elements.
	System.out.println(intlist);
	System.out.println(intlist.addAfterIf(4, 7));									// Another test on addAfterIf with 4,7 as the elements.
	System.out.println(intlist);
	System.out.println(intlist.addAfterIf(4, 9));									// Another test on addAfterIf with 4, 9 as the elements.
	System.out.println(intlist);
	System.out.println(intlist.addAfterIf(5, 7));									// Another test on addAfterIf with 5,7 as the elements.
	System.out.println(intlist);
  }
}
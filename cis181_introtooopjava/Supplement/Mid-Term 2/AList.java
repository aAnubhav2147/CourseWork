package adt;
import java.util.Arrays;// data structure

public class AList<T> implements ListInterface<T> {
	// Data
	private T[] list;
	private int numberOfEntries;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 30;
	private static final int MAX_CAPACITY = 10000;
	
	// Operations
	public AList() {
		this(DEFAULT_CAPACITY);// call another constructor
	}
	
	public AList(int initialCapacity) {
		integrityOK = false;
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(initialCapacity);
		
		T[] tempList = (T[])new Object[initialCapacity + 1];// terminate and return
		list = tempList;
		numberOfEntries = 0;
		integrityOK = true;
	}
	
	public void add(T newEntry) {
		add(numberOfEntries + 1, newEntry);
	}
	
	public void add(int givenPosition, T newEntry) {
		checkIntegrity();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1))
		{
			if (givenPosition <= numberOfEntries)
				makeRoom(givenPosition);// modular programming
			list[givenPosition] = newEntry;
			numberOfEntries++;
			ensureCapacity();//Ensure enough room for next add
		}
		else
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds");
	}
	
	public T remove(int givenPosition) {
		checkIntegrity();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			T result = list[givenPosition];
			if(givenPosition < numberOfEntries)
				removeGap(givenPosition);
			list[numberOfEntries] = null;
			numberOfEntries--;
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
	}
	
	public void clear() {
		checkIntegrity();
		for (int index = 1; index <= numberOfEntries; index++)
			list[index] = null;
		numberOfEntries = 0;
	}
	
	public T replace(int givenPosition, T newEntry) {
		checkIntegrity();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation");
	}
	
	public T getEntry(int givenPosition) {
		checkIntegrity();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			return list[givenPosition];
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation");
	}
	
	public T[] toArray() {
		checkIntegrity();
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];// downcast
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = list[index + 1];
		}
		
		return result;
	}
	
	public boolean contains(T anEntry) {
		checkIntegrity();
		boolean found = false;
		int index = 1;
		while (!found && (index <= numberOfEntries))
		{
			if(anEntry.equals(list[index]))
				found = true;
			index++;
		}
		return found;
	}
	
	public int getLength() {
		return numberOfEntries;
	}
	
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	
	// Double the capacity of the array list if it is full
	private void ensureCapacity() {
		int capacity = list.length - 1;
		if (numberOfEntries >= capacity)
		{
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity);
			list = Arrays.copyOf(list, newCapacity+1);
		}
	}
	
	private void makeRoom(int givenPosition) {
		int newIndex = givenPosition;
		int lastIndex = numberOfEntries;
		
		for (int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	}
	
	private void removeGap(int givenPosition) {
		int removedIndex = givenPosition;
		for (int index = removedIndex; index < numberOfEntries; index++)
			list[index+1] = list[index];
	}
	
	private void checkIntegrity() {
		if (!integrityOK)
			throw new SecurityException("AList object is corrupt.");
	}
	
	private void checkCapacity(int capacity) {
		if(capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a list" + "whose capacity exceeds " + "allowed maximum.");
	}
	
}

package kSmall;
//import java.util.*;

public class KthSmallest {

    private  static void swap(int[] theArray, int i, int j){
        int temp = theArray[i];
        theArray[i] = theArray[j];
        theArray[j] = temp;
    }

    private static int partition(int[] theArray, int first, int last){
    // Returns the index of the pivot element after partitioning
    // theArray[first..last]
    	int i, j;
		int pivotItem = theArray[first]; //take the first item as pivot
		//high = randomArray.length;
		
		j = first;
		//System.out.println(high);
		for(i = first + 1; i <= last; i++ ) {
			if(theArray[i] <= pivotItem) {
				j = j + 1;
				int temp1 = theArray[i];
				theArray[i] = theArray[j];
				theArray[j] = temp1;
			}			
		}
		pivotItem = j;
		int temp2 = theArray[first];
		theArray[first] = theArray[pivotItem];
		theArray[pivotItem] = temp2;
		return pivotItem;
        

        // ToDo: Determine the regions S1 and S2
        
        //Attempt 1
        /* int i = lastS1;
        //Iterate to determine the regions S1 & S2
        for(i = 0; i<=last; i++) {
        	if(theArray[i] < p) {
        		swap(theArray,i,lastS1);
            	lastS1 = lastS1 + 1;
        	}
        } */
        
        //Attempt 2
        /*
        while(first <= last) {
        	while(p > theArray[first]) {
        		lastS1 = lastS1 + 1;
        	}
        	while(p < theArray[last]) {
        		last = last - 1;
        	}
        	if(lastS1<=last) {
        		swap(theArray,first,last);
        		first++;
        		last--;
        	}
        } */
    	
    	/* Attempt 3
    	int p = theArray[last];    // use the last item of the array as the pivot (p)
        //int lastS1 = first;         // set S1 and S2 to empty
        int i = first - 1; //Set the index of a smaller element
        
        for(int j = first; j < last; j++) {
        	if(theArray[j] < p) { 
        		//check the indexes against the initialized pivot
        		i = i + 1; //move the first pointer to the right
        		swap(theArray,first,last);
        		
        	}
        }
        
        //swap(theArray,i + 1, last);
        int temp1 = theArray[i + 1];
        theArray[i + 1] = theArray[last];
        theArray[last] = temp1;
        return i + 1; */
        
        
	// Refernce the page 172 on the textbook for finding the kth smallest item of an array
        

        //return lastS1;              // the index of the pivot element
    }
    
    //Implement Quick Sort
    public static void quickSort(int[] tempArr, int min, int max) {
		int pivotPoint;
		//int recursiveCount = 0;
		if(min < max) {
			pivotPoint = partition(tempArr,min,max);
			quickSort(tempArr,min, pivotPoint-1);
			quickSort(tempArr,pivotPoint + 1, max);
			//int outCount = recursiveCount++;
			//System.out.println(outCount);
		}
	}

    public static int kSmall(int k, int[] anArray, int first, int last){
        int pivotIndex = partition(anArray, first, last);
        int p = anArray[pivotIndex]; // p is the pivot

        // ToDo: Return the kth smallest value in anArray[first..last].
	// Reference partition algorithm in QuickSort algorith, which is on page 533 of the textbook
        if(pivotIndex == k - 1)
        	//return array[k-1];
        	return anArray[pivotIndex];
 
          
        if(p > anArray[k-1]) {
        	return kSmall(k, anArray, first, pivotIndex - 1);
        }
        	
        	return kSmall(k, anArray, pivotIndex + 1, last);
        
        //return p; // Dummy return
    }
}

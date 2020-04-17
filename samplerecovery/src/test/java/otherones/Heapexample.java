package otherones;

import java.util.ArrayList;
import java.util.List;

public class Heapexample {

	
		static List<String> list = new ArrayList<String>(); 
		  
		public static void main(String args[]) throws Exception 
		    { 
			try{
				
				// Get current size of heap in bytes
				long heapSize = Runtime.getRuntime().totalMemory(); 

				// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
				long heapMaxSize = Runtime.getRuntime().maxMemory();

				 // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
				long heapFreeSize = Runtime.getRuntime().freeMemory(); 
				
				
				System.out.println("heapSize  :"+heapSize);
				System.out.println("heapMaxSize  :"+heapMaxSize);
				System.out.println("heapFreeSize  :"+heapFreeSize);
				Integer[] array1 = new Integer[100 * 100];
				System.out.println(array1.toString());
				 
				long heapSize2 = Runtime.getRuntime().totalMemory(); 
				System.out.println("heapSize2  :"+heapSize2);
				long heapFreeSize2 = Runtime.getRuntime().freeMemory();
				System.out.println("heapFreeSize2  :"+heapFreeSize2);
				
				array1=null;
				
				System.gc();
				long heapSize3 = Runtime.getRuntime().totalMemory(); 
				System.out.println("heapSize3  :"+heapSize3);
				long heapFreeSize3 = Runtime.getRuntime().freeMemory();
				System.out.println("heapFreeSize3  :"+heapFreeSize3);
				
//		        Integer[] array = new Integer[10000000 * 1000000]; 
//		        System.out.println(array.toString());
//		
		    } 
			catch(OutOfMemoryError e) {
				
			e.printStackTrace();	
			}
			finally {
				System.gc();
				long heapSize4 = Runtime.getRuntime().totalMemory();
				System.out.println("heapSize4  :"+heapSize4);
			}
	}
}



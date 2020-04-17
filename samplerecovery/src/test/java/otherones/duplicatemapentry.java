package otherones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class duplicatemapentry {

	 Map<String, ArrayList<String>> hashMapDuplicate = new HashMap<String, ArrayList<String>>();
	
	@Test
	public void testa() {
		// TODO Auto-generated method stub
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("key1", "value1");
//		map.put("key1", "value2");
//		System.out.println(map);
//		cant add duplicate value normally 
		

		
	
		
		// Add data with duplicate keys
		   addValues("Retain_items", "Car");
		   addValues("Retain_items", "Bike");
		   addValues("Scrap_items", "Desk");
		   addValues("Scrap_items", "Clothes");
		   // View data.
		   Iterator<String> it = hashMapDuplicate.keySet().iterator();
		   ArrayList<String> tempList = null;

		   while (it.hasNext()) {
		      String key = it.next().toString();             
		      tempList = hashMapDuplicate.get(key);
		      if (tempList != null) {
		         for (String value: tempList) {
		            System.out.println("Key is: "+key+ " , Value is: "+value);
		         }
		      }
		   }
		   
		   System.out.println("Generic print: ====>"+hashMapDuplicate);
	}
		   
		   public void addValues(String key, String value) {
			   ArrayList<String> tempList = null;
			   if (hashMapDuplicate.containsKey(key)) {
			      tempList = hashMapDuplicate.get(key);
			      if(tempList == null)
			         tempList = new ArrayList<String>();
			      tempList.add(value);  
			   } else {
			      tempList = new ArrayList<String>();
			      tempList.add(value);               
			   }
			   hashMapDuplicate.put(key,tempList);
			}
		   
		
	}
	
	
	
	
	
	



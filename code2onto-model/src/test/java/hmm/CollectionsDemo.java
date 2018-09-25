package hmm;

import java.util.*;

public class CollectionsDemo {

	public static void main(String[] args) {
		
		//LIST
		List<String> testList = new ArrayList<String>() {{
			add("toto");
			add("titi");
			add("tata");
			add("nini");
			add("nono");
		}};
		
		System.out.println("Element at the position 0 is :"+testList.get(0));
		
////		Map
//		Map<String, Integer> m1 = new HashMap();
//		m1.put("Zara", 12);
//		m1.put("Mahnaz", 31);
//		m1.put("Ayan", 12);
//		m1.put("Daisy", 14);
//		
//		System.out.println("Before updating");
//		
//		System.out.println();
//		System.out.println(" Map Elements");
//		System.out.print("\t" + m1);
//		
//		System.out.println("\n\nAfter updating");
//
//		m1.put("Zara", m1.get("Zara")+5);
//		
//		System.out.println();
//		System.out.println(" Map Elements");
//		System.out.print("\t" + m1);
		
//		//Linked HashMaps
//		LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>();
//		/* Populate */
//		linkedHashMap.put("key0","value0");
//		linkedHashMap.put("key1","value1");
//		linkedHashMap.put("key2","value2");
//		/* Get by position */
//		int pos = 1;
//		String value = (new ArrayList<String>(linkedHashMap.values())).get(pos);
//		linkedHashMap.
//		System.out.println("The element at the position: "+pos+" "+value);
	}

}

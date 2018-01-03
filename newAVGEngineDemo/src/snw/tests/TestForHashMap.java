package snw.tests;

import java.util.HashMap;

public class TestForHashMap {
	public static void main(String[] args)
	{
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("12", 1);
		System.out.println(map.get("12"));
		map.put("12", 12);
		System.out.println(map.get("12"));

		System.out.println(map.get("1"));
	}
}

package com.llq.demo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Demo1 {

	
	@Test
	public void test1(){
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("a", "张三");
		map.put("b", "李四");
		map.put("c", "王五");
		
		for(Map.Entry<String, Object> entry:map.entrySet()){
		
			String key=entry.getKey();
			String value=(String) map.get(key);
			System.out.println(key+"=="+value);
			
		}
		
	}
	
		
	public static void main(String[] args) {
		String str="2017AB0002";
		String str1=str.substring(0, 4);
		System.out.println(str1);
	}
		
		
	
}

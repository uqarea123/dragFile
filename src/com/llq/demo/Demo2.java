package com.llq.demo;

import org.junit.Test;

public class Demo2 {

	
	public static void test1() {

		System.out.println("上传成功！");

		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("hh");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("删除文件");
			}
		}).start();
	}
	
	public static void main(String[] args) {
		
		test1();
		
	}
		
		

}

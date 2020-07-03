package com.PeopleFinanceMgmt;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		PeopleDemo pd=(PeopleDemo)context.getBean("pd");
		
		System.out.println("1 to add\n2 to fetch\n3 to fetchOne\n4 to update\n5 to delete");
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();
        switch(i) {
        case 1:pd.add();
        break;
        case 2: pd.fetch();
        break;
        case 3:pd.fetchInfo();
        break;
        case 4: pd.update();
        break;
        case 5: pd.delete();
        }
        sc.close();
        
	}

}

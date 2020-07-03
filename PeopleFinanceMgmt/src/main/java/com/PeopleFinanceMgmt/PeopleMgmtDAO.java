package com.PeopleFinanceMgmt;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OrderBy;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;


public class PeopleMgmtDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("punit");
	EntityManager em = emf.createEntityManager();
	
	
	public void addPeopleInfo(String aadharNumber, String panNumber, String Name, int monthlyIncome) {
		panNumber=panNumber.trim();
		char c=Character.toUpperCase(panNumber.charAt(0));
		char d=Character.toUpperCase(panNumber.charAt(panNumber.length()-1));
		aadharNumber=aadharNumber.trim();
		int sp=0,i,panL=panNumber.length(),adL=aadharNumber.length();
		for(i=0;i<panL;i++) {
			if(panNumber.charAt(i)==' ')sp++;
		}
		panL=panL-sp;
		for(i=0;i<adL;i++) {
			if(aadharNumber.charAt(i)==' ')sp++;
		}
		adL-=sp;
		if(c>=65&&c<=90 && d>=48&&d<=57 &&panL==10&& adL==12) {
			try {
				People p_search=(People)em.find(People.class,aadharNumber);
				if(p_search!=null) {
					throw(new EntityExistsException("People already exists!"));
				}
				People p=new People(aadharNumber,panNumber,Name,monthlyIncome);
				em.getTransaction().begin();
				em.persist(p);
				em.getTransaction().commit();
				
				System.out.println("People added Successfully!");
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			System.out.println("Criteria to add doesn't match!");
		}
	}
	
	public List<People> fetchAllInfo() {
		String jql = "Select p from People p ORDER BY p.Name ASC";
		List<People> query;
		query=em.createQuery(jql).getResultList();
		
		/*Comparator<People> compareByName = new Comparator<People>() {
		    @Override
		    public int compare(People o1, People o2) {
		        if(o1.getName().compareTo(o2.getName())>1)
		        	return 1;
		        else return -1;
		    }
		};
		Collections.sort(query,compareByName);*/
		//System.out.println(query);
		return query;
		
	}
	public List<People> fetchInfo(){
		List<People> query=null;
		try {
			String jql = "Select p from People p where p.Name LIKE 'M%'";
			
			query=em.createQuery(jql).getResultList();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return query;
	}
	public boolean updateNameBasedOnAadharNumber(String name, String aadharNumber) {
		try {
			People p_search=(People)em.find(People.class,aadharNumber);
			
			if(p_search==null) {
				return false;
			}
			else {
				em.getTransaction().begin();
				p_search.setName(name);
				em.getTransaction().commit();
				
				return true;
			}
				
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean deletePeopleInfo(String aadharNumber) {
		try {
			People p_search=(People)em.find(People.class,aadharNumber);
			
			if(p_search==null) {
				return false;
			}
			else {
				em.getTransaction().begin();
				em.remove(p_search);
				em.getTransaction().commit();
				
				return true;
			}
				
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
}

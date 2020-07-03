package com.PeopleFinanceMgmt;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OrderBy;
import javax.persistence.Persistence;


public class PeopleMgmtDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("punit");
	EntityManager em = emf.createEntityManager();
	@OrderBy("Name ASC")
	List<People> query;
	public void addPeopleInfo(String aadharNumber, String panNumber, String Name, int monthlyIncome) {
		panNumber=panNumber.toUpperCase();
		char c=panNumber.charAt(0);
		char d=panNumber.charAt(panNumber.length()-1);
		if(c>=65&&c<=90 && d>=48&&d<=57 &&panNumber.length()==10&& aadharNumber.length()==12) {
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
		//String jql = "from People order by People.Name asc";
		
		query= em.createQuery("from People").getResultList();

		//System.out.println(query);
		return query;
		
	}
}

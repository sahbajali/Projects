package com.PeopleFinanceMgmt;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
public class PeopleDemo
{
	@Autowired
	private PeopleMgmtDAO pmd;
	
    public void add() {
    	pmd.addPeopleInfo("1234 5678 9891", "XYZPM12345" ,"John", 330000);
        pmd.addPeopleInfo("9434 9867 1253", "AXYDK10315" ,"Cater", 20000);
        //pmd.addPeopleInfo("7890 5678 9012", "GHVDK10315" ,"Mahajan", 30000);
    }
    public void fetch() {
    	//fetching all people
    	
        List<People> p=pmd.fetchAllInfo();
        if(p.size()==0) {
        	System.out.println("No records present");
        }
        else {
        	int i=0;
            for(People p1:p) {
            	i++;
            	System.out.println("People "+i +" details:");
            	System.out.println(p1.getAadharNumber());
            	System.out.println(p1.getPANNumber());
            	System.out.println(p1.getName());
            	System.out.println(p1.getMonthlyIncome());
            	System.out.println();
            }
        }
    }
    public void fetchInfo() {
    	//fetching people with name 'M...'
    	
        List<People> p=pmd.fetchInfo();
        if(p.size()!=0) {
        	int i=0;
            for(People p1:p) {
            	i++;
            	System.out.println("People "+i +" details:");
            	System.out.println(p1.getAadharNumber());
            	System.out.println(p1.getPANNumber());
            	System.out.println(p1.getName());
            	System.out.println(p1.getMonthlyIncome());
            	System.out.println();
            }
        }
        else
        	System.out.println("No records present");
        
    }
    public void update() {
    	boolean status=pmd.updateNameBasedOnAadharNumber("Motabhai","7890 5678 9012");
    	if(status) {
    		System.out.println("Record updated!");
    	}
    	else {
    		System.out.println("Update failed!");
    	}
    }
    public void delete() {
    	boolean status=pmd.deletePeopleInfo("943498671253");
    	if(status) {
    		System.out.println("Record deleted!");
    	}
    	else {
    		System.out.println("Delete failed!");
    	}
    }
}

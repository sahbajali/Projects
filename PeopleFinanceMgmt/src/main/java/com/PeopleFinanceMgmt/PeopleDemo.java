package com.PeopleFinanceMgmt;

import java.util.*;
public class PeopleDemo
{
    public static void main( String[] args )
    {
        PeopleMgmtDAO pmd=new PeopleMgmtDAO();
        //pmd.addPeopleInfo("123456789891", "XYZPM12345" ,"John", 330000);
        //pmd.addPeopleInfo("943498671253", "AXYDK10315" ,"Cater", 20000);
        //fetching people
        List<People> p=pmd.fetchAllInfo();
        int i=0;
        for(People p1:p) {
        	i++;
        	System.out.println("People "+i +" details:");
        	System.out.println(p1.getAadharNumber());
        	System.out.println(p1.getPANNumber());
        	System.out.println(p1.getName());
        	System.out.println(p1.getMonthlyIncome());
        }
        
    }
}

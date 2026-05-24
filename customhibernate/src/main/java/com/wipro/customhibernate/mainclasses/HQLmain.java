package com.wipro.customhibernate.mainclasses;

import java.time.LocalDateTime;
import java.util.List;

import com.wipro.customhibernate.Entity.Flight;
import com.wipro.customhibernate.hql.HQLFlightDAO;
import com.wipro.customhibernate.hql.HQLFlightDAOImpl;

public class HQLmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDateTime currentDateTime=LocalDateTime.now();
		HQLFlightDAO hql=new HQLFlightDAOImpl();
		Flight flight1=new Flight(14,"chennai","banglore",currentDateTime.now(),50,6000.0,40);
		//hql.createFlight(flight1);
		List<Flight> flightlist=hql.getAllFlights();
		flightlist.forEach(System.out::println);
		Flight flight11=hql.getFlightById(10);
		System.out.print(flight11);
//		hql.updateFlightPrice(10, 20000.0);
//		Flight flight11=hql.getFlightById(10);
//		System.out.print(flight11);
	}

}

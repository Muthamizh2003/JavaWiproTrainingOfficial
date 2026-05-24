package com.wipro.customhibernate.mainclasses;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.wipro.customhibernate.Entity.Flight;
import com.wipro.customhibernate.util.HibernateUtil;

public class CriteriaMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);

        Root<Flight> root = cq.from(Flight.class);

        cq.select(root);

        Query<Flight> query = session.createQuery(cq);

        List<Flight> list = query.getResultList();

        System.out.println(list);

        Query<Flight> query2 = session.createQuery(cq);

        query2.getResultStream().forEach(System.out::println);
        
		cq.select(root)
		  .where(cb.gt(root.get("ticket_price"), 5000.0));
		
		Query<Flight> query3 = session.createQuery(cq);
		
		List<Flight> list1 = query3.getResultList();
		
		list1.forEach(System.out::println);

        session.close();
    }
}
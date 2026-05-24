package com.wipro.customhibernate.mainclasses;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.wipro.customhibernate.Entity.Flight;
import com.wipro.customhibernate.util.HibernateUtil;

public class NamedMain {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query<Flight> getAllQuery=session.createNamedQuery("getAll", Flight.class);
        List<Flight> allFlights = getAllQuery.getResultList();
        allFlights.forEach(System.out::println);
        Query<Flight> getByIdQuery=session.createNamedQuery("getById", Flight.class);
        getByIdQuery.setParameter("id",14);
        Flight result = getByIdQuery.uniqueResult();
        Transaction tx = session.beginTransaction(); 
        Query deleteQuery =session.createNamedQuery("deleteById");
        deleteQuery.setParameter("id",10);
        int rows = deleteQuery.executeUpdate();
        tx.commit();
        System.out.println("Rows deleted: " +rows);
        List<Flight> afterDelete =session.createNamedQuery("getAll", Flight.class).getResultList();
        afterDelete.forEach(System.out::println);
        Query updatequery=session.createNamedQuery("greaterthan",Flight.class);
        updatequery.setParameter("price", 1000.0);
        session.close();
    }
}

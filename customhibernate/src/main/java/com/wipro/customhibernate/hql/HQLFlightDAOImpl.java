package com.wipro.customhibernate.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.wipro.customhibernate.Entity.Flight;
import com.wipro.customhibernate.util.HibernateUtil;

public class HQLFlightDAOImpl implements HQLFlightDAO {
	

	@Override
    public void createFlight(Flight flight) {
        SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        session.save(flight);
        tx.commit();
        session.close();
    }


    @Override
    public List<Flight> getAllFlights() {
        SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();
        String querystr="Select f from Flight f";
        Query<Flight> query = session.createQuery(querystr, Flight.class);
        List<Flight> list = query.getResultList();

        session.close();
        return list;
    }
    

    @Override
    public Flight getFlightById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        String queryStr = "select f from Flight f where f.id = :id";
        Query<Flight> query = session.createQuery(queryStr, Flight.class);
        query.setParameter("id",id);
        Flight flight = query.uniqueResult();
        session.close();
        return flight;
    }
    

    @Override
    public void updateFlightPrice(int id, double newPrice) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String queryStr = "update Flight f set f.ticket_price = :price where f.id = :id";
        Query query = session.createQuery(queryStr);
        query.setParameter("price", newPrice);
        query.setParameter("id", id);
        int rows = query.executeUpdate();
        System.out.println("Rows updated: " + rows);
        tx.commit();
        session.close();
    }


}
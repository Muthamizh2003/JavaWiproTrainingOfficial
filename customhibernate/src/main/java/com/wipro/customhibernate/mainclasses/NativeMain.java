package com.wipro.customhibernate.mainclasses;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.wipro.customhibernate.Entity.Flight;
import com.wipro.customhibernate.util.HibernateUtil;

public class NativeMain {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();
        session.createNativeQuery(
                "INSERT INTO Fly_tab (id, flight_start, flight_destination, start_time, seats, ticket_price, booked) " +
                "VALUES(102, 'Chennai', 'Delhi', ?, 120, 6000.0, 20)")
                .setParameter(1, Timestamp.valueOf(LocalDateTime.now()))
                .executeUpdate();
        session.createNativeQuery(
                "UPDATE Fly_tab SET ticket_price = 9000 WHERE id = 101")
                .executeUpdate();
//      session.createNativeQuery(
//                "DELETE FROM Fly_tab WHERE id = 101")
//                .executeUpdate();
        tx.commit();
        NativeQuery<Flight> query = session.createNativeQuery("SELECT * FROM Fly_tab", Flight.class);
        List<Flight> list = query.getResultList();
        list.forEach(System.out::println);
        session.close();
    }
}
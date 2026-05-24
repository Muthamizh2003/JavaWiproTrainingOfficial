package com.wipro.customhibernate.hql;

import java.util.List;
import com.wipro.customhibernate.Entity.Flight;

public interface HQLFlightDAO {
	void createFlight(Flight flight);
    List<Flight> getAllFlights();
    Flight getFlightById(int id);
    void updateFlightPrice(int id, double newPrice);

}
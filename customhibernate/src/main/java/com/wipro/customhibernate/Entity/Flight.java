package com.wipro.customhibernate.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({

    @NamedQuery(
        name = "getAll",
        query = "select f from Flight f"
    ),

    @NamedQuery(
        name = "getById",
        query = "select f from Flight f where f.id = :id"
    ),

    @NamedQuery(
        name = "deleteById",
        query = "delete from Flight f where id = :id"
    ),
    
    @NamedQuery(
            name = "greaterthan",
            query = "select f from Flight f where f.ticket_price > :price"
        )
})

@Entity
@Table(name="Fly_tab")
public class Flight {
	
	@Id
	private int id;
	
	private String flight_start;
	private String flight_destination;
	
	private LocalDateTime start_time;
	
	private int seats;
	
	private double ticket_price;
	
	private int booked;
	
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Flight(int id, String flight_start, String flight_destination, LocalDateTime start_time, int seats, double ticket_price,
			int booked) {
		super();
		this.id = id;
		this.flight_start = flight_start;
		this.flight_destination = flight_destination;
		this.start_time =start_time;
		this.seats = seats;
		this.ticket_price = ticket_price;
		this.booked = booked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlight_start() {
		return flight_start;
	}

	public void setFlight_start(String flight_start) {
		this.flight_start = flight_start;
	}

	public String getFlight_destination() {
		return flight_destination;
	}

	public void setFlight_destination(String flight_destination) {
		this.flight_destination = flight_destination;
	}

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public double getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(double ticket_price) {
		this.ticket_price = ticket_price;
	}

	public int getBooked() {
		return booked;
	}

	public void setBooked(int booked) {
		this.booked = booked;
	}

	@Override
	public String toString() {
	    return "Flight [id=" + id +
	            ", start=" + flight_start +
	            ", destination=" + flight_destination +
	            ", seats=" + seats +
	            ", price=" + ticket_price +
	            ", booked=" + booked +
	            ", starttime=" + start_time +
	            "]";
	}
	
}

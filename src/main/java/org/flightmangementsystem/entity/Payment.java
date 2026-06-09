package org.flightmangementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private ModeOfPayment modeOfTransaction;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public PaymentStatus getStatus() {
		return status;
	}


	public void setStatus(PaymentStatus status) {
		this.status = status;
	}


	public ModeOfPayment getModeOfTransaction() {
		return modeOfTransaction;
	}


	public void setModeOfTransaction(ModeOfPayment modeOfTransaction) {
		this.modeOfTransaction = modeOfTransaction;
	}


	public Booking getBooking() {
		return booking;
	}


	public void setBooking(Booking booking) {
		this.booking = booking;
	}
    
}

package com.api.ticket.Apiticket.repository;

import com.api.ticket.Apiticket.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TIcketRepository extends JpaRepository<Ticket, Long> {



}

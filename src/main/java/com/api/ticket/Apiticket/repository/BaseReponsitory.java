package com.api.ticket.Apiticket.repository;

import com.api.ticket.Apiticket.Model.BaseConnaissance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseReponsitory extends JpaRepository<BaseConnaissance,Long> {
}

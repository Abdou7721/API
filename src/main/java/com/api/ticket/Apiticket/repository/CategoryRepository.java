package com.api.ticket.Apiticket.repository;

import com.api.ticket.Apiticket.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

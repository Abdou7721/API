package com.api.ticket.Apiticket.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}

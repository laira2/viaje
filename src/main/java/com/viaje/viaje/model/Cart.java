package com.viaje.viaje.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String cart_id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    public Users users;

}
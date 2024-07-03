package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long tagId;

    @Column(nullable = false, unique = true)
    public String tagName;

}
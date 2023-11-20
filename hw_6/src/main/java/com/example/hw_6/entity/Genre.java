package com.example.hw_6.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "genres")
@Entity
public class Genre {
    @Id
    long id;
    String name;
}

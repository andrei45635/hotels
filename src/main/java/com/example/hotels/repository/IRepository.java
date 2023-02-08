package com.example.hotels.repository;

import com.example.hotels.domain.Entity;

import java.util.List;

public interface IRepository<ID, E extends Entity<Double>>{
    List<E> findAll();
}

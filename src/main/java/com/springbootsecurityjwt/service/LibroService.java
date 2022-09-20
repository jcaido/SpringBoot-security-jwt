package com.springbootsecurityjwt.service;

import com.springbootsecurityjwt.entity.Libro;

import java.util.List;

public interface LibroService {

    Libro save(Libro libro);

    List<Libro> findAll();
}

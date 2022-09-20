package com.springbootsecurityjwt.service;

import com.springbootsecurityjwt.entity.Libro;
import com.springbootsecurityjwt.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService{

    private LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro save(Libro libro) {
        return this.libroRepository.save(libro);
    }

    @Override
    public List<Libro> findAll() {
        return this.libroRepository.findAll();
    }
}

package com.springbootsecurityjwt.controller;

import com.springbootsecurityjwt.entity.Libro;
import com.springbootsecurityjwt.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibroController {

    private LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping("/libros")
    public ResponseEntity<Libro> create(@RequestBody Libro libro) {

        if (libro.getId() != null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(this.libroService.save(libro));
    }

    @GetMapping("/libros")
    public List<Libro> findAll() {

        return this.libroService.findAll();
    }
}

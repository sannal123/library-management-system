package com.sannal.librarymanagement.entity;

import jakarta.persistence.*;
//import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true)
    private  String isbn;

    private String category;

    private Integer quantity;

    private boolean available;
}

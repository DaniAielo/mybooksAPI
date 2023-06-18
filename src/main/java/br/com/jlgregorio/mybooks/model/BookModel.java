package br.com.jlgregorio.mybooks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorModel author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;



}

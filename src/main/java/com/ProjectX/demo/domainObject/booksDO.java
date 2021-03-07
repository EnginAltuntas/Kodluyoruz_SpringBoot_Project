package com.ProjectX.demo.domainObject;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="books")
public class booksDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String book_name;
    @Column(nullable = false)
    private String author_name;

    private  int numberOfPages;


}

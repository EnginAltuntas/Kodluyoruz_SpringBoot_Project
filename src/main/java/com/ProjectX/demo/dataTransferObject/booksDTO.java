package com.ProjectX.demo.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class booksDTO implements Serializable {

    @JsonIgnore
    private Long id;

    private String book_name;
    private String author_name;
    private int numberOfPages;


}
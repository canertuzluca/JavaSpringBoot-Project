package io.github.canertuzluca.javaspringboot.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String author;

    // for Date
    private Date date = new Date();

    // For image
    private String image;

}



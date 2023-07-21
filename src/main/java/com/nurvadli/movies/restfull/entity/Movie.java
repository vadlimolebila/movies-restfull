package com.nurvadli.movies.restfull.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * @author Nurvadli
 */
@Data
@Entity
public class Movie implements Serializable {
    private static final long serialVersionUID = -2147468513335906679L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Float rating;
    private String image;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}

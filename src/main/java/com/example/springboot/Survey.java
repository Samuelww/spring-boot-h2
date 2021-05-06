package com.example.springboot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;



@Entity
@Table(name="survey")
public class Survey implements Serializable {
@Id
    private Long _id;
}
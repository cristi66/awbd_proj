package com.awbd.entities;

import com.awbd.enums.TransactionTypeEnum;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "The minimum amount for a transaction is 0")
    private int amount;

    private String description;

    private Date createdOn;

    @Enumerated(value = EnumType.STRING)
    private TransactionTypeEnum type;

    @ManyToOne
    private Users user;
}

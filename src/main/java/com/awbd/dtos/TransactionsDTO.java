package com.awbd.dtos;

import com.awbd.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDTO {
    private int amount;
    private String description;
    private Date createdOn;
    private TransactionTypeEnum type;
}

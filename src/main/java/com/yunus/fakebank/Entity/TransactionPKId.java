package com.yunus.fakebank.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TransactionPKId implements Serializable {
    private Long givenId;
    private Long receivenId;
    private String dateTime;
}

package com.bookstore.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Transaction {
    private List<String> items;
}

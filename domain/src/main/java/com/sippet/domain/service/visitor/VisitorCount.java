package com.sippet.domain.service.visitor;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(staticName = "create")
public class VisitorCount {
    private int total;
    private int newVisitor;
    private LocalDate date;
}

package com.sippet.api;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "create")
class VisitorCount {
    int total;
    int newVisitor;
}

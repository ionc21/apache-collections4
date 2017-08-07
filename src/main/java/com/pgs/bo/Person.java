package com.pgs.bo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class Person {
    private String name;
    private String lastName;
    private String email;
    private String address;
}

package com.pgs.bag;

import com.pgs.bo.Person;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import java.util.Arrays;

public class BagExample {

    public static void main(String[] args) {
        Person p = new Person("Vasia", "Ignat", "vignat@gmail.com", "Bulonska 15");
        Person e = new Person("Vasia", "Ignat", "vignat@gmail.com", "Bulonska 15");
        Person p1 = new Person("Andrei", "Indoitu", "ai@gmail.com", "Vadul lui Voda 21/3");
        Person p2 = new Person("Alina", "Stan", "alinastan@gmail.com", "Armeneasca 23");

        Bag<Person> bag = new HashBag<>(Arrays.asList(p, e, p1, p2));
        System.out.println("Count numbers of the same object: " +bag.getCount(p));

    }
}

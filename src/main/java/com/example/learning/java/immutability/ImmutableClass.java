package com.example.learning.java.immutability;

import lombok.Data;

@Data
public  final class ImmutableClass {
    private final int a = 10;
    private final int b =20;
    ImmutableClass() {

    }
}

//the class if final
// reason-1 -> child can override parent methods and can break the contract of immutability of parent
//getname(), can give different name at different time
//constructor leaking problem can happen -> in parent constructor, we can have some function
//which is overridden by child, when calling from child parent constructor will be called
// and it will invoke but due to polymorphism child overridden method get invoked
// which can try to access attributes  which is instance and can throw exception
//hashcode can be overridden which can cause issue in map insertion

package com.example.learning.LLDPractice.CommonDesignPatterns.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class Product {
    int stock;
    List<ObserverCustom> observerCustomList = new ArrayList<>();

    public void addObserver(ObserverCustom observerCustom) {
       observerCustomList.add(observerCustom);
    }

    public void removeObserver(ObserverCustom observerCustom) {
        observerCustomList.remove(observerCustom);
    }

    public void notifyObserver() {
        for(ObserverCustom observerCustom : observerCustomList) {
            observerCustom.notifyCustom();
        }
    }

    public void setStock(int stock) {
        this.stock = stock;
        if(stock>0) {
            notifyObserver();
        }
    }
}

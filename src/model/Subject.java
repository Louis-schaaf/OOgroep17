package model;

import jxl.read.biff.BiffException;
import model.bestelling.BestellingEvents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Subject {
    Map<String, ArrayList<Observer>> observers = new HashMap<>();
    public void addObserver(Observer observer, String event);
    public void removeObserver(Observer observer);

    public void notifyObservers(String event) throws IOException, BiffException;
}

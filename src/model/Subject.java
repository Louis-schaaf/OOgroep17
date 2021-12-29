package model;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Deze interface geeft alle mogelijke acties die de verschillende Subjects kunnen uitvoeren.
 */
public interface Subject {
    Map<String, ArrayList<Observer>> observers = new HashMap<>();
    void addObserver(Observer observer, String event);
    void removeObserver(Observer observer, String event);
    void notifyObservers(String event) throws IOException, BiffException;
}

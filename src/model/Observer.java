package model;

import jxl.read.biff.BiffException;

import java.io.IOException;

/**
 * Deze interface geeft alle mogelijke acties die de verschillende Observers kunnen uitvoeren.
 */
public interface Observer {
    void update() throws IOException, BiffException;
}

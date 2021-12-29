package model;

import jxl.read.biff.BiffException;

import java.io.IOException;

public interface Observer {
    public void update(String event) throws IOException, BiffException;
}

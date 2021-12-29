package model;

import jxl.read.biff.BiffException;

import java.io.IOException;

public interface Observer {
    public void update() throws IOException, BiffException;
}

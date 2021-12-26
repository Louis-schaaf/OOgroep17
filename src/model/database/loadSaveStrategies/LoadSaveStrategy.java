package model.database.loadSaveStrategies;

import model.Broodje;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LoadSaveStrategy {
    Map load();
    void save(List arrayList);
}

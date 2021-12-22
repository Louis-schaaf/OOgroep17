package model.database.loadSaveStrategies;

import java.io.File;
import java.util.ArrayList;

public interface LoadSaveStrategy {
    void load();
    void save();
}

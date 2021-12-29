package model.database.loadSaveStrategies;

import java.util.List;
import java.util.Map;

/**
 * Deze interface geeft alle mogelijke acties die de verschillende LoadStrategies kunnen uitvoeren.
 */
public interface LoadSaveStrategy {
    Map load();
    void save(List arrayList);
}

package Offer;

import java.util.HashMap;
import java.util.Map;

public class OfferManager {
  private Map<String, Offer> offerPrototypes = new HashMap<>();

  public void addOfferPrototype(String key, Offer prototype) {
    offerPrototypes.put(key, prototype);
  }

  public Offer getOfferClone(String key) throws CloneNotSupportedException {
    Offer prototype = offerPrototypes.get(key);
    if (prototype != null) {
      return prototype.clone();
    }
    return null;
  }
}

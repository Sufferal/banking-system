package Offer;

public interface OfferSubject {
  void registerObserver(OfferObserver observer);
  void removeObserver(OfferObserver observer);
  void notifyObservers(Offer offer);
}

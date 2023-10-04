package Offer;

public class BonusPointsOffer implements Offer {
  private int points;

  public BonusPointsOffer(int points) {
    this.points = points;
  }

  @Override
  public Offer clone() throws CloneNotSupportedException {
    return (BonusPointsOffer) super.clone();
  }

  public int getPoints() {
    return points;
  }

  @Override
  public String toString() {
    return "BonusPointsOffer[ " + points + " points" + " ]";
  }
}

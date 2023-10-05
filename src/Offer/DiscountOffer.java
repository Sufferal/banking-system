package Offer;

public class DiscountOffer implements Offer {
  private String description;

  public DiscountOffer(String description) {
    this.description = description;
  }

  @Override
  public Offer clone() throws CloneNotSupportedException {
    return (DiscountOffer) super.clone();
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "DiscountOffer[ " + description + " ]";
  }
}

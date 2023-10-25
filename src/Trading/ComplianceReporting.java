package Trading;

public class ComplianceReporting {
  public void generateReport(String tradeConfirmation) {
    if (tradeConfirmation.equals("Trade successful")) {
      System.out.println("Compliance report generated and filed.");
    } else {
      System.out.println("Trade failed. No compliance report generated.");
    }
  }
}

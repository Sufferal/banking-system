package Utils.Strategy;

public class FingerprintAuthentication implements Authentication {
  private final int[][] userFingerprint;

  public FingerprintAuthentication(int[][] fingerprint) {
    this.userFingerprint = fingerprint;
  }

  @Override
  public boolean authenticate() {
    System.out.println("Scanning fingerprint...");
    int[][] inputFingerprintMatrix = captureUserFingerprint();
    boolean authenticationSuccess = compareFingerprintMatrices(inputFingerprintMatrix, userFingerprint);

    if (authenticationSuccess) {
      System.out.println("Fingerprint authentication successful.");
    } else {
      System.out.println("Fingerprint authentication failed. Please try again.");
    }

    return authenticationSuccess;
  }

  private int[][] captureUserFingerprint() {
    return new int[][] {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}
    };
  }

  private boolean compareFingerprintMatrices(int[][] inputMatrix, int[][] referenceMatrix) {
    if (inputMatrix == null || referenceMatrix == null) {
      return false;
    }

    if (inputMatrix.length != referenceMatrix.length || inputMatrix[0].length != referenceMatrix[0].length) {
      return false;
    }

    for (int i = 0; i < inputMatrix.length; i++) {
      for (int j = 0; j < inputMatrix[i].length; j++) {
        if (inputMatrix[i][j] != referenceMatrix[i][j]) {
          return false;
        }
      }
    }

    return true;
  }
}

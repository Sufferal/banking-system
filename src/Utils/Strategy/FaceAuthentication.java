package Utils.Strategy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FaceAuthentication implements Authentication {
  private final BufferedImage userFaceImage;

  public FaceAuthentication(String imagePath) {
    try {
      this.userFaceImage = ImageIO.read(new File(imagePath));
    } catch (Exception e) {
      throw new RuntimeException("Error reading image file");
    }
  }

  @Override
  public boolean authenticate() {
    System.out.println("Scanning face...");
    BufferedImage inputFaceImage = captureFaceImage();
    boolean authenticationSuccess = compareFaceImages(inputFaceImage, userFaceImage);

    if (authenticationSuccess) {
      System.out.println("Face authentication successful.");
    } else {
      System.out.println("Face authentication failed. Please try again.");
    }

    return authenticationSuccess;
  }

  private BufferedImage captureFaceImage() {
    try {
      BufferedImage img = ImageIO.read(new File("src/Utils/Strategy/img/face_test.jpg"));
      return img;
    } catch (Exception e) {
      throw new RuntimeException("Error reading image file");
    }
  }

  private boolean compareFaceImages(BufferedImage inputFace, BufferedImage referenceFace) {
    return inputFace != null && referenceFace != null && inputFace.getWidth() == referenceFace.getWidth()
      && inputFace.getHeight() == referenceFace.getHeight();
  }
}

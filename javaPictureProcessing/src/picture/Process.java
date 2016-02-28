package picture;

public class Process {

private final int MAX_INTENSITY = 255;
private final int NUM_OF_BASES = 3;
private final int NUM_OF_COLOURS_IN_MATRIX = 9;
private Picture picture;

 
  public Process(Picture picture) {
    this.picture = picture;
  }
 
  public Picture getPicture() {
    return picture;
  } 

  public void invert() {
    for (int i = 0; i < picture.getHeight(); i++) {
     for (int j = 0; j < picture.getWidth(); j++) {
       Color colour = picture.getPixel(j, i);

       colour.setRed((MAX_INTENSITY) - colour.getRed());
       colour.setGreen((MAX_INTENSITY) - colour.getGreen());
       colour.setBlue((MAX_INTENSITY) - colour.getBlue());

       picture.setPixel(j, i, colour);
     }
    }
  }
   
  public void grayscale() {
    for (int i = 0; i < picture.getHeight(); i++) {
     for (int j = 0; j < picture.getWidth(); j++) {
      Color colour = picture.getPixel(j,i);

      int avg = (colour.getRed() + colour.getBlue()
          + colour.getGreen())/NUM_OF_BASES;
      colour.setRed(avg);
      colour.setGreen(avg);
      colour.setBlue(avg);
    
      picture.setPixel(j, i, colour); 
     }
    } 
  }
  
  public void flipVertical() {
  
  int halfHeight = picture.getHeight()/2;
    
    for (int i = 0; i < halfHeight; i++) {
      for (int j = 0; j < picture.getWidth(); j++) {
        Color original = picture.getPixel(j,i);
        
        int otherY = picture.getHeight() - (i + 1);
        Color toSwapWith = picture.getPixel(j, otherY);
        picture.setPixel(j, i, toSwapWith);
        picture.setPixel(j, otherY, original); 
      }
    }
  }
 
  public void flipHorizontal() {
  
  int halfWidth = picture.getWidth()/2;
    
    for (int i = 0; i < picture.getHeight(); i++) {
      for (int j = 0; j < halfWidth; j++) {
        Color original = picture.getPixel(j, i);

        int otherX = picture.getWidth() - (j+1);
        Color toSwapWith = picture.getPixel(otherX, i);
        picture.setPixel(j, i, toSwapWith);
        picture.setPixel(otherX, i, original);
      }
    }
  }

  public void rotate(int angle) {
      Picture newImage = Utils.createPicture(picture.getHeight()
              , picture.getWidth());
    if (angle == 90) {
      for (int i = 0; i < picture.getHeight(); i++) {
        for (int j = 0; j < picture.getWidth(); j++) {
          Color colour = picture.getPixel(j,i);
          newImage.setPixel(picture.getHeight() - (i + 1),
                  j, colour);
        } 
      }
    }
  picture = newImage;
  }
 
  public void blur() {

  Picture newImage = Utils.createPicture(picture.getWidth()
      , picture.getHeight());
  
    for (int i = 0; i < picture.getHeight(); i++) {
      for (int j = 0; j < picture.getWidth(); j++) {
        if (picture.contains(j-1,i-1) &&
            picture.contains(j+1,i+1)) {

            int matAverageRed = 0;
            int matAverageBlue = 0;
            int matAverageGreen = 0;

            for (int y = i - 1; y < i + 2; y++) {
                for (int x = j - 1; x < j + 2; x++) {
                    matAverageRed += picture.getPixel(x, y).getRed();
                    matAverageBlue += picture.getPixel(x, y).getBlue();
                    matAverageGreen += picture.getPixel(x, y).getGreen();
                }
            }

            matAverageRed /= NUM_OF_COLOURS_IN_MATRIX;
            matAverageBlue /= NUM_OF_COLOURS_IN_MATRIX;
            matAverageGreen /= NUM_OF_COLOURS_IN_MATRIX;

          Color colour = new Color(matAverageRed,matAverageGreen,matAverageBlue);
          newImage.setPixel(j, i, colour);
       } else {
          newImage.setPixel(j,i,picture.getPixel(j,i));
       }
     }
   } picture = newImage;
  }

  /*  My Blend Function dosen't work properly
   ** And I have run out of time to complete it however
   ** I am still in the process of debugging it,
   ** It's basically there.
   */
  public void blend(Picture[] images) {
    int width = images[0].getWidth();
    int height = images[0].getHeight();
    for (int i = 1; i < images.length; i++) {
        if (images[i].getHeight() < height) {
            height = images[i].getHeight();
        }
        if (images[i].getWidth() < width) {
            width = images[i].getWidth();
        }
    }
    Picture blendedPic = Utils.createPicture(width,height);

      for (int i = 0; i < blendedPic.getHeight(); i++) {
        for (int j = 0; j < blendedPic.getWidth(); j++) {
          int red = 0;
          int green = 0;
          int blue = 0;
          for (Picture image : images) {


          red += image.getPixel(j, i).getRed();
          blue += image.getPixel(j, i).getBlue();
          green += image.getPixel(j, i).getGreen();

        }
          int numOfPics = (images.length)-1;
          int redAverage = red/numOfPics;
          int greenAverage = green/numOfPics;
          int blueAverage = blue/numOfPics;
          Color newColour = new Color(redAverage,greenAverage,blueAverage);
          blendedPic.setPixel(j, i, newColour);
      }
    }
    picture = blendedPic;
  }
}
 

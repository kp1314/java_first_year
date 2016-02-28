package picture;

public class Main {

  public static void main(String[] args) {

    if (args[0] != null) {
      switch (args[0]) {
      case ("invert"):
      case ("grayscale"):
      case ("blur"):
        Picture image = Utils.loadPicture(args[1]);
        Process process = new Process(image);
          if (args[0].equals("invert")) {
            process.invert();
          }
          if (args[0].equals("grayscale")) {
            process.grayscale();
          }
          if (args[0].equals("blur")) {
            process.blur();
          }
        Utils.savePicture(process.getPicture(), args[2]); break;
      case ("rotate"):
      case ("flip"):
        Picture image1 = Utils.loadPicture(args[2]);
        Process process1 = new Process(image1);
        if (args[0].equals("flip")) {
          if ("H".equals(args[1])) {
            process1.flipHorizontal();
          } 
          if ("V".equals(args[1])) {
            process1.flipVertical();
          }
        }
        if (args[0].equals("rotate")) {
          int rotation = Integer.parseInt(args[1]);
             if (rotation == 90) {
                 process1.rotate(rotation);
             }
             if (rotation == 180) {
                 process1.rotate(90);
                 process1.rotate(90);
             }
             if (rotation == 270) {
                 process1.rotate(90);
                 process1.rotate(90);
                 process1.rotate(90);
             }
        }
        Utils.savePicture(process1.getPicture(), args[3]); break;
      case ("blend"):
        Picture[] images = new Picture[args.length-2];
          for(int i = 0; i < images.length; i++) {
             Picture image2 = Utils.loadPicture(args[i+1]);
             images[i] = image2;
          }
        Process process2 = new Process(images[0]);
        process2.blend(images);
        Utils.savePicture(process2.getPicture(), args[args.length-1]); break;
      default: System.out.println("Sorry, Method Not Defined");
      }
    }
  }
}

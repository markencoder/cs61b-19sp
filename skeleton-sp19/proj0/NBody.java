public class NBody{
  public static double readRadius(String filename ){
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
  }
  public static Body[] readBodies(String filename){
    In in = new In(filename);
     int num = in.readInt();
     in.readDouble();
     Body[] Planets = new Body[num];

     int i = 0;
     while (i < num) {
         double xP = in.readDouble();
         double yP = in.readDouble();
         double xV = in.readDouble();
         double yV = in.readDouble();
         double m = in.readDouble();
         String img = in.readString();
         Planets[i++] = new Body(xP, yP, xV, yV, m, img);
     }
     return Planets;
   }
   public static void main(String[] args) {
     double T = Double.parseDouble(args[0]);
     double dt = Double.parseDouble(args[1]);
     String filename = args[2];
     double R = readRadius(filename);
     Body[] bodies = readBodies(filename);
     /** start to draw a universe */
    /* StdDraw.enableDoubleBuffering();*/
     StdDraw.setScale(-R, R);
     StdDraw.clear();

     StdDraw.picture(0, 0, "images/starfield.jpg");

     for (Body body : bodies) {
       body.draw();
     }

     StdDraw.enableDoubleBuffering(); 

     for (double t = 0; t <= T ; t = t + dt ) {
       double[] xForces = new double[bodies.length];
       double[] yForces = new double[bodies.length];

      for (int i=0; i<bodies.length; i++) {
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
      }
      for (int i=0; i<bodies.length; i++) {
        bodies[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
           /**
            * Draw all planets
            */
      for (Body body : bodies) {
               body.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
     }
   }
}

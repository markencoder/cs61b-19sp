public class Body {
  double xxPos;
  double yyPos;
  double xxVel;
  double yyVel;
  double mass;
  String imgFileName;
  static final double g = 6.67e-11;
    public Body(double xP, double yP, double xV, double yV, double m, String img){
      xxPos = xP;
      yyPos = yP;
      xxVel = xV;
      yyVel = yV;
      mass = m;
      imgFileName = img;
    }

    public Body( Body b){
      xxPos = b.xxPos;
      yyPos = b.yyPos;
      xxVel = b.xxVel;
      yyVel = b.yyVel;
      mass = b.mass;
      imgFileName = b.imgFileName;
    }
    public double calcDistance (Body b1){
      return Math.sqrt((this.xxPos - b1.xxPos)*(this.xxPos - b1.xxPos) + (this.yyPos - b1.yyPos) * (this.yyPos - b1.yyPos) );
    }
    public double calcForceExertedBy(Body b1){
      double r = this.calcDistance(b1);
      return g * this.mass * b1.mass / (r*r);
    }
    public double calcForceExertedByX(Body b1){
      return this.calcForceExertedBy(b1) * (b1.xxPos - this.xxPos) / this.calcDistance(b1);
    }
    public double calcForceExertedByY(Body b1){
      return this.calcForceExertedBy(b1) * (b1.yyPos - this.yyPos) / this.calcDistance(b1);
    }
    public double calcNetForceExertedByX(Body []allBodys){
      int i = 0;
      double F = 0;
      while (i < allBodys.length) {
        if (this.equals(allBodys[i])) {

        }else{
          F = F + this.calcForceExertedByX(allBodys[i]);
        }
        i = i + 1;
      }
      return F;
    }
    public double calcNetForceExertedByY(Body []allBodys){
      int i = 0;
      double F = 0;
      while (i < allBodys.length) {
        if (this.equals(allBodys[i])) {

        }else{
          F = F + this.calcForceExertedByY(allBodys[i]);
        }
        i = i + 1;
      }
      return F;
    }
    public void update(double dt,double fX,double fY){
      double a_x_net;
      double a_y_net;
      a_x_net = fX / this.mass;
      a_y_net = fY / this.mass;
      this.xxVel = this.xxVel + a_x_net * dt;
      this.yyVel = this.yyVel + a_y_net * dt;
      this.xxPos = this.xxVel * dt + this.xxPos;
      this.yyPos = this.yyVel * dt + this.yyPos;
    }
    public void draw(){
      StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}

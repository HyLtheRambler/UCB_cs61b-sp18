public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;

    /* Constructors for Planet */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
                  xxPos = xP;
                  yyPos = yP;
                  xxVel = xV;
                  yyVel = yV;
                  mass = m;
                  imgFileName = img;
                  }
    public Planet(Planet P) {
        this.xxPos = P.xxPos;
        this.yyPos = P.yyPos;
        this.xxVel = P.xxVel;
        this.yyVel = P.yyVel;
        this.mass = P.mass;
        this.imgFileName = P.imgFileName;
    }

    /* Claculate the distance between two Planets */
    public double calcDistance(Planet rocinante) {
        double dxx = rocinante.xxPos - this.xxPos;
        double dyy = rocinante.yyPos - this.yyPos;
        double result = Math.sqrt(dxx * dxx + dyy * dyy);
        return result;
    }
    
    public double calcForceExertedBy(Planet rocinante) {
        return G * this.mass * rocinante.mass / this.calcDistance(rocinante) / this.calcDistance(rocinante);
    }

    public double calcForceExertedByX(Planet rocinante) {
        double dxx = rocinante.xxPos - this.xxPos;
        if (dxx < 0) {
            dxx = -dxx;
        }
        return this.calcForceExertedBy(rocinante) * (rocinante.xxPos - this.xxPos) / this.calcDistance(rocinante);
    }

    public double calcForceExertedByY(Planet rocinante) {
        double dyy = rocinante.yyPos - this.yyPos;
        if (dyy < 0) {
            dyy = -dyy;
        }
        return this.calcForceExertedBy(rocinante) * (rocinante.yyPos - this.yyPos) / this.calcDistance(rocinante);
    }

    public double calcNetForceExertedByX(Planet[] allplanets) {
        int len = allplanets.length;
        double result = 0;
        for (int j = 0; j < len; ++j) {
            if (this.equals(allplanets[j])) {
                continue;
            }
            result += this.calcForceExertedByX(allplanets[j]);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] allplanets) {
        int len = allplanets.length;
        double result = 0;
        for (int j = 0; j < len; ++j) {
            if (this.equals(allplanets[j])) {
                continue;
            }
            result += this.calcForceExertedByY(allplanets[j]);
        }
        return result;
    }

    /* Calculate the acceleration */
    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
        return;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }


    public double calcDistance(Planet p) {
        double xxDiff = this.xxPos - p.xxPos;
        double yyDiff = this.yyPos - p.yyPos;
        return Math.sqrt(xxDiff * xxDiff + yyDiff * yyDiff);
    }

    public double calcForceExertedBy(Planet p) {
        double dist = calcDistance(p);
        return G * this.mass * p.mass / (dist * dist);
    }

    public double calcForceExertedByX(Planet p) {
        double dist = calcDistance(p);
        double force = calcForceExertedBy(p);
        return (p.xxPos - this.xxPos) / dist * force;
    }

    public double calcForceExertedByY(Planet p) {
        double dist = calcDistance(p);
        double force = calcForceExertedBy(p);
        return (p.yyPos - this.yyPos) / dist * force;
    }

    public double calcNetForceExertedByX(Planet[] others) {
        double totalForce = 0;
        for (Planet other : others) {
            if (this.equals(other))
                continue;
            totalForce += calcForceExertedByX(other);
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] others) {
        double totalForce = 0;
        for (Planet other : others) {
            if (this.equals(other))
                continue;
            totalForce += calcForceExertedByY(other);
        }
        return totalForce;
    }

    public void update(double duration, double xxForce, double yyForce) {
        double xxAcc = xxForce / this.mass;
        double yyAcc = yyForce / this.mass;
        double newXXVel = this.xxVel + duration * xxAcc;
        double newYYVel = this.yyVel + duration * yyAcc;
        this.xxVel = newXXVel;
        this.yyVel = newYYVel;
        this.xxPos = this.xxPos + duration * newXXVel;
        this.yyPos = this.yyPos + duration * newYYVel;
    }
    

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}

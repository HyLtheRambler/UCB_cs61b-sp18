public class NBody {

    public static double readRadius(String filename) {
        In in = new In(filename);
        int numOfPlanet = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numOfPlanet = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numOfPlanet];
        int i = 0;
        while (i < numOfPlanet) {
            Planet tmp = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), 
                        in.readDouble(), in.readDouble(), in.readString());
            planets[i] = tmp; 
            i++;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] Planets = readPlanets(filename);
        double Radius = readRadius(filename);

        StdDraw.setScale(-Radius, Radius);
        StdDraw.clear();

        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for (Planet i : Planets) {
            i.draw();
        }
        
        StdDraw.enableDoubleBuffering();
        int len = Planets.length;
        double time = 0.;
        while (time <= T) {
            double[] xForces = new double[len];
            double[] yForces = new double[len];
            int i = 0;
            for (; i < len; ++i) {
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }
            i = 0;
            for (; i < len; ++i) {
                Planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet j : Planets) {
                j.draw();
            }
            
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
}
    }
}

public class NBody{
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In in = new In(filename);
		int numPlanets = in.readInt();
		double radUniverse = in.readDouble();
		Planet[] lstPlanets = new Planet[numPlanets];
		int index = 0;
		while (index<numPlanets){
			lstPlanets[index] = getPlanet(in);
			index = index + 1;
		}
		StdDraw.setScale(-radUniverse,radUniverse);
		StdAudio.play("audio/2001.mid");
		double time = 0;
		while(time <= T){
			for(Planet elem:lstPlanets){
				elem.setNetForce(lstPlanets);
			}
			for(Planet elem:lstPlanets){
				elem.update(dt);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Planet item:lstPlanets){
				item.draw();
			}
			StdDraw.show(10);
			time = time + dt;
		}
		StdOut.printf("%d\n", numPlanets);
		StdOut.printf("%.2e\n", radUniverse);
		for (int i = 0; i < numPlanets; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            lstPlanets[i].x, lstPlanets[i].y, lstPlanets[i].xVelocity,
            lstPlanets[i].yVelocity, lstPlanets[i].mass, lstPlanets[i].img);
}
	}
	public static Planet getPlanet(In input){
		double x = input.readDouble();
		double y = input.readDouble();
		double xVelocity = input.readDouble();
		double yVelocity = input.readDouble();
		double mass = input.readDouble();
		String img = input.readString();
		Planet jeeves = new Planet(x,y,xVelocity,yVelocity,mass,img);
		return jeeves;
	}
}
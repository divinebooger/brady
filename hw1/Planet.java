public class Planet{	
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double mass;
	public String img;
	public double xNetForce;
	public double yNetForce;
	public double xAccel = 0;
	public double yAccel = 0;
	public Planet(double one, double two, double three, double four, 
		double five, String six){
		x = one;
		y = two;
		xVelocity = three;
		yVelocity = four;
		mass = five;
		img = six;
	}
	public double calcDistance(Planet p1){
		return Math.sqrt((p1.x - x)*(p1.x - x) + (p1.y - y)*(p1.y - y));
	}
	public double calcPairwiseForce(Planet p2){
		return 0.0000000000667*mass*p2.mass/(this.calcDistance(p2)*this.calcDistance(p2));
	}
	public double calcPairwiseForceX(Planet p3){
		return this.calcPairwiseForce(p3)*(p3.x-x)/this.calcDistance(p3);
	}
	public double calcPairwiseForceY(Planet p4){
		return this.calcPairwiseForce(p4)*(p4.y-y)/this.calcDistance(p4);
	}
	public void setNetForce(Planet[] p5){
		int index = 0;
		xNetForce = 0;
		yNetForce = 0;
		while (index<p5.length){
			if (p5[index]!= this){
				xNetForce = xNetForce + this.calcPairwiseForceX(p5[index]);
				yNetForce = yNetForce + this.calcPairwiseForceY(p5[index]);
			}
			index = index + 1;
		}
	}
	public void draw(){
		StdDraw.picture(x,y,"images/"+img);
	}
	public void update(double dt){
		xAccel = xNetForce/this.mass;
		yAccel = yNetForce/this.mass;
		xVelocity = xVelocity + dt*xAccel;
		yVelocity = yVelocity + dt*yAccel;
		x = x + dt*xVelocity;
		y = y + dt*yVelocity;
	}
}
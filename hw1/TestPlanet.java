public class TestPlanet{
	public static void main(String[] args) {
		In jeeves = new In("armageddon.txt");
		int s = jeeves.readInt();
		System.out.println(s);
		double q = jeeves.readDouble();
		System.out.println(q);
		double r = jeeves.readDouble();
		System.out.println(r);
		double t = jeeves.readDouble();
		System.out.println(t);
		double u = jeeves.readDouble();
		System.out.println(u);
	}
}
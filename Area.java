package secondyear;



class shape {
	double area() {
		System.out.println("Default area");
		return 4;
	}
}

class Circle extends shape{
	int radius ;
	Circle(int radius){
		this.radius=radius;
	}
	double area() {
		return 3.14*radius*radius;
	}
}
class Rectangle extends shape{
	double length;
	double breadth;
	Rectangle(double length,double breadth){
		this.length=length;
		this.breadth=breadth;
}
	double area() {
		return length*breadth;
	}
}

public class Area{
	public static void main(String[] args) {
		Circle o1=new Circle(5);
		Rectangle o2=new Rectangle(10,5);
		System.out.println("area of circle "+o1.area());
		System.out.println();
		System.out.println("area of rectangle "+o2.area());
		System.out.println();
		shape o= new shape();
		System.out.println(o.area());
		System.out.println();
		
		Circle o3=new Circle(0);
		System.out.println("area of circle "+o3.area());
		System.out.println();
		Rectangle o4=new Rectangle(10,0);
		System.out.println("Area of rectangle "+o4.area());
		System.out.println();
		System.out.println("Name: DHARSHINI V");
		System.out.println("Reg.no:2117240020077");

	
	}
}
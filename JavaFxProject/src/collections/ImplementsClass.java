package collections;

interface Runnable<T>{
	public void run(T t);
}

class Car implements Runnable<String>{

	@Override
	public void run(String str) {
		System.out.println("자동차가 달립니다.");
	}
}
class bus implements Runnable<String>{
	@Override
	public void run(String str) {
		System.out.println("버스가 달립니다.");
	}
}
public class ImplementsClass {
	// 메소드 구현.
	public static void callRun(Runnable<String> runnable) {
		runnable.run("Hello");
	}
	
	public static void main(String[] args) {
		Runnable<String> runnable = new Car();
		runnable.run("Car");
		runnable = new bus();
		runnable.run("Bus");
		runnable = (str)-> System.out.println("익명 객체 달려요");
		runnable.run("Run");
		callRun(runnable);
		callRun(new Car());
		callRun( (str)-> System.out.println("메소드 매개값 달립니다.") );
	}
}

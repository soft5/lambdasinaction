package lambdasinaction.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

	public static void main(String ... args) {
		List<Apple> inventory = Arrays.asList(
				new Apple(80,"green"), 
				new Apple(155, "green"), 
				new Apple(120, "red"));
	
	List<Apple> greenApples = filterAppleByColor(inventory, "green");
	System.out.println(greenApples);
	
	List<Apple> redApples = filterAppleByColor(inventory, "red");
	System.out.println(redApples);
	
	List<Apple> greenAppes2 = filter(inventory, new AppleColorPredicate());
	System.out.println(greenAppes2);
	
	List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
	System.out.println(heavyApples);
	
	List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
	System.out.println(redAndHeavyApples);
	
	List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
		@Override
		public boolean test(Apple a) {
			return a.getColor().equals("red");
		}
	});
	System.out.println(redApples2);
	}
	
	public static List<Apple> filterGreenApples(List<Apple> inventory){
		ArrayList<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterAppleByColor(List<Apple> inventory,String color){
		ArrayList<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterAppleByWeight(List<Apple> inventory,int weight){
		ArrayList<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight()>weight) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filter(List<Apple> inventory,ApplePredicate p){
		ArrayList<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" +
					"color='" + color + '\'' +
					", weight=" + weight +
					'}';
		}
	}
	
	public interface ApplePredicate{
		public boolean test(Apple a);
	}
	
	static class AppleWeightPredicate implements ApplePredicate{
		@Override
		public boolean test(Apple apple) {
			return apple.getWeight()>150;
		}
	}
	
	static class AppleColorPredicate implements ApplePredicate{
		@Override
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}
	
	static class AppleRedAndHeavyPredicate implements ApplePredicate{
		@Override
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor())&&apple.getWeight()>150;
		}
	}
}

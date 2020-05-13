package lambdasinaction.chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

	public static void main(String ... args) {
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                							new Apple(155, "green"),
                							new Apple(120, "red"));
		//Java 8会把条件代码作为参数传递进去
		List<Apple> greenApples = filterApples(inventory,FilteringApples::isGreenApple);
		System.out.println(greenApples);		
		List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
		System.out.println(heavyApples);
		
		//Lambda
		List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
		System.out.println(greenApples2);
		List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight()>150);
		System.out.println(heavyApples2);
		List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight()>70&&"red".equals(a.getColor()));
		System.out.println(weirdApples);
		
	}
	/**
	 * Java 8之前，筛选绿苹果
	 * @param inventory
	 * @return
	 */
	public static List<Apple> filterGreenApples(List<Apple> inventory){
		ArrayList<Apple> result = new ArrayList<>();
		for(Apple apple : inventory) {
			if("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}
	/**
	 * Java 8之前，筛选重量超过150的苹果
	 * @param inventory
	 * @return
	 */
	public static List<Apple> filterHeavyApples(List<Apple> inventory){
		ArrayList<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if(apple.getWeight()>150) {
				result.add(apple);
			}
		}
		return result;
	}
	/**
	 * Java 8
	 * @param inventory
	 * @param p
	 * @return
	 */
	public static List<Apple> filterApples(List<Apple> inventory,Predicate<Apple> p){
		ArrayList<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	//Java 8会把条件代码作为参数传递进去
	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}
	//Java 8会把条件代码作为参数传递进去
	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight()>150;
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
}

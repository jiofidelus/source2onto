package test.model;

public class Car {

	String id;
	String brand;
	int year;
	String color;
	int price;
	public Car(String randomId, String randomBrand, int randomYear, 
			String randomColor, int randomPrice,
			boolean randomSoldState) {
		super();
		this.id = randomId;
		this.brand = randomBrand;
		this.year = randomYear;
		this.color = randomColor;
		this.price = randomPrice;
		this.randomSoldState = randomSoldState;
	}

	boolean randomSoldState;
	
	public Car() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isRandomSoldState() {
		return randomSoldState;
	}

	public void setRandomSoldState(boolean randomSoldState) {
		this.randomSoldState = randomSoldState;
	}

}

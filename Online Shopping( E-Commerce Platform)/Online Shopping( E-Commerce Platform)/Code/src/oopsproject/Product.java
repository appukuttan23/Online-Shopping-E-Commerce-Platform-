package oopsproject;

public class Product {
	private String id;
	private String name;
	private String price;
	
	public Product(String id,String name,String price) {
		this.id=id;
		this.name=name;
		this.price=price;
	}
	
	public String getid() {
		return id;
	}
	
	public String getname() {
		return name;
		
	}
	
	public String getprice() {
		return price;
	}

}

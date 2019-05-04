package ProductSales;

public class Product {
	
	String productType;
	int productValue;
	int noOfSales;
	int totalValue;
	String operation; 
	public Product() {
	}

	public Product(String type) {
		this.totalValue = 0;
		this.noOfSales = 0;
		this.productType = type;
	}
	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	
	public int getNoOfSales() {
		return noOfSales;
	}

	public void setNoOfSales(int noOfSales) {
		this.noOfSales = noOfSales;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getProductValue() {
		return productValue;
	}

	public void setProductValue(int productValue) {
		this.productValue = productValue;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int calculatePrice(int productQuantity, int productPrice) {
		return productQuantity * productPrice;
	}

	public void addTotalPrice(int productPrice) {
		this.totalValue += productPrice;
	}
}

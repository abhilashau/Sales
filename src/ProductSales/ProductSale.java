package ProductSales;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductSale {
	Product productSale;
	int totalPrice;
	int priceAftrAdjustment;
    ArrayList<String> logReports = new ArrayList();;
    ArrayList<String> adjustedReports = new ArrayList();;
    HashMap<String, Product> logMessages = new HashMap();
	public ProductSale() {
		//this.totalPrice = 0;
	}

	public boolean processMessage(String message) {
		MessageReader messageReader = new MessageReader(message);
		Product product = messageReader.getProduct();
		if (product.getProductType() == null) {
			return false;
		}
		
		this.productSale = getProduct(product.getProductType());
		this.productSale.setProductType(product.getProductType());
		this.productSale.setNoOfSales(product.getNoOfSales());
		this.productSale.setProductValue(product.getProductValue());
		this.productSale.setOperation(product.getOperation());
		calculateTotalPrice(this.productSale);
		setLogReports(message);
		updateProduct(this.productSale);
		return true;
	}
	
	private void calculateTotalPrice(Product productInfo) {
		//int priceAfterAdjustment;
		int productValue;

		if (productInfo.getOperation() != null) {
			this.priceAftrAdjustment = getPriceAfterAdjustment();
			setAdjustedReports(adjustedReport());
			this.productSale.setTotalValue(this.priceAftrAdjustment);
		} else {
			productValue = (int) productInfo.calculatePrice(productInfo.getNoOfSales(), productInfo.getProductValue());
			this.productSale.addTotalPrice(productValue);
		}
	}
	
	public int getPriceAfterAdjustment() {
		String adjustmentMethod = this.productSale.getOperation().toLowerCase();
		if(adjustmentMethod.equalsIgnoreCase("add")) {
			this.priceAftrAdjustment = addPrice();
		} else if(adjustmentMethod.equalsIgnoreCase("subtract")) {
			this.priceAftrAdjustment = subtractPrice();
		} else {
			this.priceAftrAdjustment = multiplyPrice();
		}
		return this.priceAftrAdjustment;
	}

	public int addPrice() {
		return this.productSale.getTotalValue()
				+ (this.productSale.getNoOfSales() * this.productSale.getProductValue());
	}

	public int subtractPrice() {
		return this.productSale.getTotalValue()
				- (this.productSale.getNoOfSales() * this.productSale.getProductValue());
	}

	public int multiplyPrice() {
		return this.productSale.getTotalValue()
				+ (this.productSale.getTotalValue() * this.productSale.getProductValue())
				+ (this.productSale.getNoOfSales() * this.productSale.getProductValue());
	}
	
	public void setLogReports(String logReport) {
		this.logReports.add(logReport);
	}

	public void setAdjustedReports(String adjustedReport) {
		this.adjustedReports.add(adjustedReport);
	}
	
	public ArrayList getAdjustedReports() {
		return this.adjustedReports;
	}
	
	public Product getProduct(String type) {
		return logMessages.getOrDefault(type, new Product(type));
	}
	
	public void updateProduct(Product product) {
		logMessages.put(product.getProductType(), product);
	}

	// Getting all the reports that have been processed so far.
	public ArrayList getLogReports() {
		return this.logReports;
	}
	
	public double getTotalSalesValue() {
		return this.productSale.getTotalValue();
	}

	public void appendTotalSalesValue(int productTotalPrice) {
		this.productSale.setTotalValue(this.productSale.getTotalValue()+productTotalPrice);
	}

	public void setTotalSalesValue(int productTotalPrice) {
		this.productSale.setTotalValue(productTotalPrice);
	}
	
	public void logReportsMessages(String type, Product product) {
		String LogMessage = product.getProductType()+ "       " +product.getNoOfSales()+ "      "+
				product.getTotalValue();
		appendTotalSalesValue(product.getTotalValue());
		System.out.println(LogMessage);
	}
	
	public String adjustedReport() {
		String adjustmentReport = "Executed "
				+this.productSale.getOperation()+" " +this.productSale.getProductValue()+ " to " + this.productSale.getNoOfSales()
				+" " +this.productSale.getProductType() + " and price adjusted from " + this.productSale.getTotalValue()+ " to "+this.priceAftrAdjustment;
		return adjustmentReport;
	}
	
	public void recordSales() {
				// Report logged after successful 10th iteration.
				if (logReports.size() == 10 && logReports.size() != 0) {
					setTotalSalesValue(0);
					System.out.println("-------10 sales reached and messages appended to log-------");
					System.out.println("*************** Log Report *****************");
					System.out.println("Product          Quantity         Price      ");
					logMessages.forEach((k, v) -> logReportsMessages(k, v));
					System.out.println("-------------------------------------------");
					System.out.println(String.format("Total Sales", getTotalSalesValue()));
					System.out.println("-------------------------------------------");
					System.out.println("End\n");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// Report logged and stop execution after 50th message.
				if ((logReports.size() % 50) == 0 && logReports.size() != 0) {
					System.out.println(
							"Application reached 50 messages and cannot process further. The below are the adjustment records prepared.\n");

					getAdjustedReports().forEach(System.out::println);
					System.exit(1);
				}
	}
	
}

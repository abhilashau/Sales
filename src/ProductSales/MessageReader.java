package ProductSales;

public class MessageReader {
		Product product = new Product();
		
	public MessageReader(String message) {
		product.setProductValue(0);
		product.setNoOfSales(0);
		readMessage(message);
	}

	public void readMessage(String message) {
		if (message != null) {
			String[] messages = message.split("\\s+");
			if (messages.length == 3) {
				if (messages[0].startsWith("Add") || messages[0].startsWith("Subtract")
						|| messages[0].startsWith("Multiply")) {
					product.setProductType(getType(messages[2].toString()));
					product.setProductValue(getPrice(messages[1].toString()));
					product.setOperation(messages[0].toString());
					product.setNoOfSales(0);
				} else {
					product.setProductType(getType(messages[0].toString()));
					product.setProductValue(getPrice(messages[2].toString()));
					product.setNoOfSales(1);
				}
			}
			if (messages.length > 3 && messages.toString().contains("at")) {
				product.setProductType(getType(messages[3].toString()));
				product.setProductValue(getPrice(messages[5].toString()));
				product.setNoOfSales(Integer.parseInt(messages[0].toString()));
			}
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getPrice(String price) {
		int finalPrice = Integer.parseInt(price.replaceAll("£|p", ""));
		return finalPrice;
	}
	
	public String getType(String type) {
		String parsedType = "";
		String typeWithoutLastChar = type.substring(0, type.length() - 1);
		if (type.endsWith("o")) {
			parsedType = String.format("%soes", typeWithoutLastChar);
		} else if (type.endsWith("y")) {
			parsedType = String.format("%sies", typeWithoutLastChar);
		} else if (type.endsWith("h")) {
			parsedType = String.format("%shes", typeWithoutLastChar);
		} else if (!type.endsWith("s")) {
			parsedType = String.format("%ss", type);
		} else {
			parsedType = String.format("%s", type);
		}
		return parsedType.toLowerCase();
	}
	 
	
}

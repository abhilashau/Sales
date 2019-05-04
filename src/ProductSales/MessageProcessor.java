package ProductSales;

import java.io.BufferedReader;
import java.io.FileReader;

public class MessageProcessor {

	public static void main(String[] args) {
		ProductSale productSale = new ProductSale();
		
		try {
			String line;
			BufferedReader inputFile = new BufferedReader(new FileReader("ProductsMessages.txt"));
			while ((line = inputFile.readLine()) != null) {
				productSale.processMessage(line);
				productSale.recordSales();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

}

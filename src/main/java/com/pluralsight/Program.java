package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Program {
    public static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();
    public static void main(String[] args) {
        HashMap<String, Product> productCollection = loadInventory();
        String itemName = "dsffsdf";
        Product product = productCollection.get(itemName);
        System.out.println(product);
        System.out.println();

        printItems(productCollection);

    }

    public static HashMap<String, Product> loadInventory() {
        HashMap<String,Product> productCollection = new HashMap<>();

        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] itemAttributes = input.split("\\|");
                Product currentProduct = new Product();
                currentProduct.setId(Integer.parseInt(itemAttributes[0]));
                currentProduct.setName(itemAttributes[1]);
                currentProduct.setPrice(Double.parseDouble(itemAttributes[2]));
                productCollection.put(currentProduct.getName() ,currentProduct);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading the inventory from the file.");
            e.printStackTrace();
        }
        return productCollection;
    }
    public static void printItems(HashMap<String, Product> items){
        for(Product item: items.values()) {
            System.out.println("{ Item name: " + item.getName() + ", ID: " + item.getId() + ", Price: $" +
                    item.getPrice() + " }");
        }
    }
}

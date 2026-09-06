package com.hossam.fitnessshop.repository;

import com.hossam.fitnessshop.model.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
  private String filePath;

  public ProductRepository(String filePath) {
    this.filePath = filePath;
  }

  public List<Product> loadProducts() {
    Path path = Path.of(this.filePath);
    List<Product> products = new ArrayList<Product>();
    try (BufferedReader reader = Files.newBufferedReader(path)) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        String[] productData = line.split(",");
        Product product =
            new Product(
                Integer.parseInt(productData[0]),
                productData[1],
                productData[2],
                Double.parseDouble(productData[3]));
        products.add(product);
      }
    } catch (IOException e) {
      System.err.format("IOException : %s%n", e);
    }
    return products;
  }

  public void saveProducts(List<Product> products) {
    Path path = Path.of(this.filePath);
    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
      for (Product product : products) {
        String line =
            product.getId()
                + ","
                + product.getName()
                + ","
                + product.getDescription()
                + ","
                + product.getPrice()
                + "\n";
        writer.write(line);
      }

    } catch (IOException e) {
      System.err.format("IOException : %s%n", e);
    }
  }
}

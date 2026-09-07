package com.hossam.fitnessshop.model;

import java.util.Collections;
import java.util.List;
import com.hossam.fitnessshop.repository.ProductRepository;

public class Catalog {
  private List<Product> products;
  private ProductRepository productRepository;

  public Catalog(ProductRepository productRepository) {
    this.products = productRepository.loadProducts();
    this.productRepository = productRepository;
  }

  public List<Product> getAllProducts() {
    return Collections.unmodifiableList(this.products);
  }

  public Product findProductById(int id) {
    for (Product product : this.products) {
      if (product.getId() == id) {
        return product;
      }
    }
    return null;
  }

  public int getNextId() {
    if (this.products.isEmpty()) {
      return 1;
    }
    int candidateId = 0;
    for (Product product : this.products) {
      if (product.getId() > candidateId) {
        candidateId = product.getId();
      }
    }
    return ++candidateId;
  }

  public void addProduct(String name, String description, double price) {
    Product product = new Product(getNextId(), name, description, price);
    this.products.add(product);
    productRepository.saveProducts(this.products);
  }

  public boolean removeProduct(int id) {
    Product product = findProductById(id);
    if (product == null) {
      return false;
    } else {
      boolean isRemoved = this.products.remove(product);
      if (isRemoved) {
        productRepository.saveProducts(this.products);
      }
      return isRemoved;
    }
  }

  public boolean modifyProduct(int id, String newName, String newDescription, double newPrice) {
    Product product = findProductById(id);
    if (product == null) {
      return false;
    }
    product.setName(newName);
    product.setDescription(newDescription);
    product.setPrice(newPrice);
    productRepository.saveProducts(this.products);
    return true;
  }
}

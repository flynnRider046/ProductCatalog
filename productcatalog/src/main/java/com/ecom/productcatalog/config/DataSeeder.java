package com.ecom.productcatalog.config;

import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.repository.CategoryRepository;
import com.ecom.productcatalog.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
// Command Line runner helps you to run a piece of code when a spring boot application starts
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DataSeeder(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // clearing all the existing data
        categoryRepository.deleteAll();
        productRepository.deleteAll();
        // creating categories
        Category electronics = new Category();
        electronics.setName("Electronics");
        Category clothing = new Category();
        clothing.setName("Clothing");
        Category home = new Category();
        home.setName("Home and Kitchen");

        categoryRepository.saveAll(Arrays.asList(electronics,clothing,home));

        // Create products
        // electronics
        Product phone = new Product();
        phone.setName("Samsung Galaxy S4 mini");
        phone.setDescription("Latest Smart phone with high resolution and Advanced camera");
        phone.setImageUrl("https://placehold.co/600x400");
        phone.setPrice(699.99);
        phone.setCategory(electronics);

        Product laptop = new Product();
        laptop.setName("Apple MacBook pro");
        laptop.setDescription("Latest apple mac book pro with m2 chip and advanced features");
        laptop.setImageUrl("https://placehold.co/600x400");
        laptop.setPrice(1299.99);
        laptop.setCategory(electronics);

        //clothing
        Product jacket = new Product();
        jacket.setName("Zara Men's Jacket");
        jacket.setDescription("Blue color jacket for different occations");
        jacket.setImageUrl("https://placehold.co/600x400");
        jacket.setPrice(30.59);
        jacket.setCategory(clothing);

        Product tShirt = new Product();
        tShirt.setName("Puma T shirt");
        tShirt.setDescription("Summer t-shirt from puma with cooling feature");
        tShirt.setImageUrl("https://placehold.co/600x400");
        tShirt.setPrice(11.99);
        tShirt.setCategory(clothing);

        //home and kitchen
        Product airFryer = new Product();
        airFryer.setName("Air Fryer Latest");
        airFryer.setDescription("Air Fryer to reduce cooking with oil and for good health");
        airFryer.setImageUrl("https://placehold.co/600x400");
        airFryer.setPrice(60.23);
        airFryer.setCategory(home);

        Product television = new Product();
        television.setName("TCL");
        television.setDescription("8' inch advanced television");
        television.setImageUrl("https://placehold.co/600x400");
        television.setPrice(500.23);
        television.setCategory(home);

        productRepository.saveAll(Arrays.asList(phone, laptop, jacket, tShirt, airFryer, television));



    }
}

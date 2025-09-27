package com.cheko.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheko.app.entities.CategoryEntity;
import com.cheko.app.entities.MenuEntity;
import com.cheko.app.entities.RestaurantEntity;
import com.cheko.app.repositories.CategoryRepository;
import com.cheko.app.repositories.MenuRepository;
import com.cheko.app.repositories.RestaurantRepository;

@RestController
@RequestMapping("/api/seed")
public class SeedingController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping("/all")
    public ResponseEntity<String> seedAllData() {

        // Clear existing data
        menuRepository.deleteAll();
        categoryRepository.deleteAll();
        restaurantRepository.deleteAll();

        // Create Categories
        List<CategoryEntity> categories = createCategories();

        // Create Restaurants
        List<RestaurantEntity> restaurants = createRestaurants();

        // Create Menu Items
        createMenuItems(categories, restaurants);

        return ResponseEntity.ok("✅ All sample data seeded successfully!");
    }

    private List<CategoryEntity> createCategories() {
        List<CategoryEntity> categories = new ArrayList<>();

        CategoryEntity burgers = new CategoryEntity();
        burgers.setName("Burgers");
        categories.add(categoryRepository.save(burgers));

        CategoryEntity pizza = new CategoryEntity();
        pizza.setName("Pizza");
        categories.add(categoryRepository.save(pizza));

        CategoryEntity drinks = new CategoryEntity();
        drinks.setName("Drinks");
        categories.add(categoryRepository.save(drinks));

        CategoryEntity desserts = new CategoryEntity();
        desserts.setName("Desserts");
        categories.add(categoryRepository.save(desserts));

        CategoryEntity salads = new CategoryEntity();
        salads.setName("Salads");
        categories.add(categoryRepository.save(salads));

        return categories;
    }

    private List<RestaurantEntity> createRestaurants() {
        List<RestaurantEntity> restaurants = new ArrayList<>();

        RestaurantEntity chekoMain = new RestaurantEntity();
        chekoMain.setName("Cheko Main Restaurant");
        chekoMain.setLet(24.7136);
        chekoMain.setLng(46.6753);
        restaurants.add(restaurantRepository.save(chekoMain));

        RestaurantEntity chekoBranch1 = new RestaurantEntity();
        chekoBranch1.setName("Cheko - Al Malqa Branch");
        chekoBranch1.setLet(24.7500);
        chekoBranch1.setLng(46.6900);
        restaurants.add(restaurantRepository.save(chekoBranch1));

        RestaurantEntity chekoBranch2 = new RestaurantEntity();
        chekoBranch2.setName("Cheko - Al Nakheel Branch");
        chekoBranch2.setLet(24.6800);
        chekoBranch2.setLng(46.7200);
        restaurants.add(restaurantRepository.save(chekoBranch2));

        return restaurants;
    }

    private void createMenuItems(List<CategoryEntity> categories, List<RestaurantEntity> restaurants) {
        CategoryEntity burgers = categories.get(0);
        CategoryEntity pizza = categories.get(1);
        CategoryEntity drinks = categories.get(2);
        CategoryEntity desserts = categories.get(3);
        CategoryEntity salads = categories.get(4);

        RestaurantEntity mainRestaurant = restaurants.get(0);

        // Burgers
        MenuEntity chickenBurger = new MenuEntity();
        chickenBurger.setName("Spicy Chicken Burger");
        chickenBurger.setDescription("Grilled chicken burger with fresh vegetables and spicy sauce");
        chickenBurger.setPrice(new BigDecimal("28.50"));
        chickenBurger.setCalories(450);
        chickenBurger.setImageUrl("https://example.com/chicken-burger.jpg");
        chickenBurger.setCategory(burgers);
        chickenBurger.setRestaurant(mainRestaurant);
        menuRepository.save(chickenBurger);

        MenuEntity beefBurger = new MenuEntity();
        beefBurger.setName("Classic Beef Burger");
        beefBurger.setDescription("Premium beef burger with cheese and fresh vegetables");
        beefBurger.setPrice(new BigDecimal("35.00"));
        beefBurger.setCalories(520);
        beefBurger.setImageUrl("https://example.com/beef-burger.jpg");
        beefBurger.setCategory(drinks);
        beefBurger.setRestaurant(mainRestaurant);
        menuRepository.save(beefBurger);

        // Pizza
        MenuEntity margherita = new MenuEntity();
        margherita.setName("Margherita Pizza");
        margherita.setDescription("Classic pizza with tomatoes, cheese and basil");
        margherita.setPrice(new BigDecimal("32.00"));
        margherita.setCalories(380);
        margherita.setImageUrl("https://example.com/margherita.jpg");
        margherita.setCategory(pizza);
        margherita.setRestaurant(mainRestaurant);
        menuRepository.save(margherita);

        // Drinks
        MenuEntity cola = new MenuEntity();
        cola.setName("Cola");
        cola.setDescription("Refreshing carbonated soft drink");
        cola.setPrice(new BigDecimal("8.00"));
        cola.setCalories(150);
        cola.setImageUrl("https://example.com/cola.jpg");
        cola.setCategory(pizza);
        cola.setRestaurant(mainRestaurant);
        menuRepository.save(cola);

        // Desserts
        MenuEntity cheesecake = new MenuEntity();
        cheesecake.setName("Berry Cheesecake");
        cheesecake.setDescription("Creamy cheesecake with fresh berries");
        cheesecake.setPrice(new BigDecimal("25.00"));
        cheesecake.setCalories(320);
        cheesecake.setImageUrl("https://example.com/cheesecake.jpg");
        cheesecake.setCategory(desserts);
        cheesecake.setRestaurant(mainRestaurant);
        menuRepository.save(cheesecake);

        // Salads
        MenuEntity caesarSalad = new MenuEntity();
        caesarSalad.setName("Caesar Salad");
        caesarSalad.setDescription("Classic salad with grilled chicken and cheese");
        caesarSalad.setPrice(new BigDecimal("22.00"));
        caesarSalad.setCalories(280);
        caesarSalad.setImageUrl("https://example.com/caesar-salad.jpg");
        caesarSalad.setCategory(desserts);
        caesarSalad.setRestaurant(mainRestaurant);
        menuRepository.save(caesarSalad);
    }
}

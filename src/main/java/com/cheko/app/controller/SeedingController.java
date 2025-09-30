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

        CategoryEntity breakfast = new CategoryEntity();
        breakfast.setName("Breakfast");
        breakfast.setImageUrl(
                "https://www.freeiconspng.com/thumbs/breakfast-png/breakfast-transparent-background-23.png");
        categories.add(categoryRepository.save(breakfast));

        CategoryEntity drinks = new CategoryEntity();
        drinks.setName("Drinks");
        drinks.setImageUrl(
                "https://images.vexels.com/media/users/3/156830/isolated/preview/8ee91ecc00f00bc5b2ca2b83bfd4de8b-margarita-cocktail-flat-icon.png");
        categories.add(categoryRepository.save(drinks));

        CategoryEntity soups = new CategoryEntity();
        soups.setName("Soups");
        soups.setImageUrl(
                "https://png.pngtree.com/png-vector/20230412/ourmid/pngtree-hot-soup-line-icon-vector-png-image_6702836.png");
        categories.add(categoryRepository.save(soups));

        CategoryEntity sushi = new CategoryEntity();
        sushi.setName("Sushi");
        sushi.setImageUrl(
                "https://cdn-icons-png.flaticon.com/512/5391/5391778.png");
        categories.add(categoryRepository.save(sushi));

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
        RestaurantEntity mainRestaurant = restaurants.get(0);

        // Create 8-10 items for each category
        for (CategoryEntity category : categories) {
            createMenuItemsForCategory(category, mainRestaurant);
        }
    }

    private void createMenuItemsForCategory(CategoryEntity category, RestaurantEntity restaurant) {
        String categoryName = category.getName().toLowerCase();

        switch (categoryName) {
            case "breakfast":
                createBreakfastItems(category, restaurant);
                break;
            case "drinks":
                createDrinkItems(category, restaurant);
                break;
            case "soups":
                createSoupItems(category, restaurant);
                break;
            case "sushi":
                createSushiItems(category, restaurant);
                break;
            default:
                // Default items if category doesn't match
                createDefaultItems(category, restaurant);
                break;
        }
    }

    private void createBreakfastItems(CategoryEntity category, RestaurantEntity restaurant) {
        String[] names = {
                "Classic Pancakes", "French Toast", "Eggs Benedict", "Avocado Toast",
                "Breakfast Burrito", "Omelette Special", "Granola Bowl", "Waffles with Berries",
                "English Breakfast", "Shakshuka"
        };

        String[] descriptions = {
                "Fluffy pancakes served with maple syrup and butter",
                "Thick brioche slices with cinnamon and vanilla",
                "Poached eggs on English muffin with hollandaise sauce",
                "Fresh avocado on artisan bread with tomatoes",
                "Scrambled eggs, cheese, and vegetables wrapped in tortilla",
                "Three-egg omelette with your choice of fillings",
                "Homemade granola with fresh fruits and yogurt",
                "Belgian waffles topped with fresh berries and cream",
                "Traditional breakfast with eggs, bacon, beans, and toast",
                "Eggs poached in spiced tomato sauce"
        };

        BigDecimal[] prices = {
                new BigDecimal("18.50"), new BigDecimal("16.00"), new BigDecimal("24.50"),
                new BigDecimal("19.00"), new BigDecimal("22.00"), new BigDecimal("20.50"),
                new BigDecimal("15.00"), new BigDecimal("17.50"), new BigDecimal("28.00"),
                new BigDecimal("21.00")
        };

        int[] calories = { 420, 380, 450, 320, 380, 350, 280, 400, 520, 290 };

        for (int i = 0; i < names.length; i++) {
            MenuEntity item = createMenuItem(names[i], descriptions[i], prices[i],
                    calories[i], category, restaurant);
            menuRepository.save(item);
        }
    }

    private void createDrinkItems(CategoryEntity category, RestaurantEntity restaurant) {
        String[] names = {
                "Fresh Orange Juice", "Iced Coffee", "Green Tea", "Mango Smoothie",
                "Cola", "Lemonade", "Cappuccino", "Mineral Water",
                "Energy Drink", "Hot Chocolate"
        };

        String[] descriptions = {
                "Freshly squeezed orange juice",
                "Cold brew coffee with ice and milk",
                "Premium green tea with antioxidants",
                "Fresh mango blended with yogurt",
                "Classic carbonated soft drink",
                "Fresh lemon juice with sparkling water",
                "Espresso with steamed milk foam",
                "Premium sparkling mineral water",
                "Caffeine boost energy drink",
                "Rich chocolate drink with whipped cream"
        };

        BigDecimal[] prices = {
                new BigDecimal("12.00"), new BigDecimal("8.50"), new BigDecimal("6.00"),
                new BigDecimal("14.00"), new BigDecimal("5.50"), new BigDecimal("7.00"),
                new BigDecimal("9.50"), new BigDecimal("4.50"), new BigDecimal("11.00"),
                new BigDecimal("10.00")
        };

        int[] calories = { 110, 25, 2, 140, 150, 60, 80, 0, 110, 200 };

        for (int i = 0; i < names.length; i++) {
            MenuEntity item = createMenuItem(names[i], descriptions[i], prices[i],
                    calories[i], category, restaurant);
            menuRepository.save(item);
        }
    }

    private void createSoupItems(CategoryEntity category, RestaurantEntity restaurant) {
        String[] names = {
                "Tomato Basil Soup", "Chicken Noodle Soup", "Mushroom Cream Soup", "Lentil Soup",
                "Minestrone", "Corn Chowder", "French Onion Soup", "Vegetable Soup",
                "Beef Broth"
        };

        String[] descriptions = {
                "Rich tomato soup with fresh basil leaves",
                "Traditional chicken soup with egg noodles",
                "Creamy mushroom soup with herbs",
                "Hearty lentil soup with vegetables",
                "Italian vegetable soup with pasta",
                "Sweet corn soup with cream",
                "Classic onion soup with cheese croutons",
                "Mixed seasonal vegetables in clear broth",
                "Rich beef bone broth with herbs"
        };

        BigDecimal[] prices = {
                new BigDecimal("16.00"), new BigDecimal("18.50"), new BigDecimal("17.00"),
                new BigDecimal("15.50"), new BigDecimal("16.50"), new BigDecimal("17.50"),
                new BigDecimal("19.00"), new BigDecimal("14.00"), new BigDecimal("20.00")
        };

        int[] calories = { 180, 220, 240, 190, 170, 210, 250, 120, 150 };

        for (int i = 0; i < names.length; i++) {
            MenuEntity item = createMenuItem(names[i], descriptions[i], prices[i],
                    calories[i], category, restaurant);
            menuRepository.save(item);
        }
    }

    private void createSushiItems(CategoryEntity category, RestaurantEntity restaurant) {
        String[] names = {
                "Salmon Nigiri", "Tuna Sashimi", "California Roll", "Avocado Roll",
                "Spicy Tuna Roll", "Rainbow Roll", "Dragon Roll", "Tempura Roll",
                "Salmon Teriyaki Roll", "Cucumber Roll"
        };

        String[] descriptions = {
                "Fresh salmon over seasoned rice",
                "Premium tuna sashimi slices",
                "Crab, cucumber, and avocado roll",
                "Fresh avocado with sushi rice",
                "Spicy tuna with cucumber",
                "Mixed fish roll with colorful toppings",
                "Eel and cucumber with avocado topping",
                "Crispy tempura shrimp roll",
                "Grilled salmon with teriyaki sauce",
                "Simple cucumber roll for vegetarians"
        };

        BigDecimal[] prices = {
                new BigDecimal("24.00"), new BigDecimal("28.00"), new BigDecimal("22.00"),
                new BigDecimal("18.00"), new BigDecimal("25.00"), new BigDecimal("32.00"),
                new BigDecimal("30.00"), new BigDecimal("26.00"), new BigDecimal("27.00"),
                new BigDecimal("16.00")
        };

        int[] calories = { 200, 180, 240, 160, 220, 280, 260, 250, 230, 140 };

        for (int i = 0; i < names.length; i++) {
            MenuEntity item = createMenuItem(names[i], descriptions[i], prices[i],
                    calories[i], category, restaurant);
            menuRepository.save(item);
        }
    }

    private void createDefaultItems(CategoryEntity category, RestaurantEntity restaurant) {
        // Generic items for any category not specifically handled
        for (int i = 1; i <= 8; i++) {
            String name = category.getName() + " Item " + i;
            String description = "Delicious " + category.getName().toLowerCase() + " dish number " + i;
            BigDecimal price = new BigDecimal(15 + (i * 2));
            int calories = 200 + (i * 20);

            MenuEntity item = createMenuItem(name, description, price, calories, category, restaurant);
            menuRepository.save(item);
        }
    }

    private MenuEntity createMenuItem(String name, String description, BigDecimal price,
            int calories, CategoryEntity category, RestaurantEntity restaurant) {
        MenuEntity item = new MenuEntity();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setCalories(calories);
        item.setImageUrl("https://via.placeholder.com/300x200?text=" + name.replace(" ", "+"));
        item.setCategory(category);
        item.setRestaurant(restaurant);
        return item;
    }
}

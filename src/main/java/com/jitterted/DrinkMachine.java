package com.jitterted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jitterted.IngredientName.*;

public class DrinkMachine {

    private final List<Ingredient> ingredientList = new ArrayList<>();
    private final Drinks drinks = new Drinks();

    public DrinkMachine() {
        initializeIngredientList();
        drinks.initializeDrinkList(ingredientList);
        drinks.updateDrinkList(ingredientList);
        drinks.updateMakeable();
    }

    private void initializeIngredientList() {
        ingredientList.add(new Ingredient(COFFEE, 0.75));
        ingredientList.add(new Ingredient(DECAF_COFFEE, 0.75));
        ingredientList.add(new Ingredient(SUGAR, 0.25));
        ingredientList.add(new Ingredient(CREAM, 0.25));
        ingredientList.add(new Ingredient(STEAMED_MILK, 0.35));
        ingredientList.add(new Ingredient(FOAMED_MILK, 0.35));
        ingredientList.add(new Ingredient(ESPRESSO, 1.10));
        ingredientList.add(new Ingredient(COCOA, 0.90));
        ingredientList.add(new Ingredient(WHIPPED_CREAM, 1.00));

        Collections.sort(ingredientList);
    }

    public static void main(String[] args) {
        DrinkMachine drinkMachine = new DrinkMachine();
        drinkMachine.displayInventoryAndMenu();
        drinkMachine.startIO();
    }


    public void startIO() {
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while (true) {
            try {
                input = reader.readLine().toLowerCase();
                processInput(input);
            } catch (IOException e) {
                System.out.println("No idea why we got an IOException here." + e);
            }
        }
    }

    private void processInput(String input) {
        if (input.isBlank()) {
            return;
        } else if (userQuits(input)) {
            System.exit(0);
        } else if (userRestocked(input)) {
            restockIngredients();
            drinks.updateMakeable();
        } else if (drinks.isValidSelection(input)) {
            makeDrink(drinks.drinkChoice(input));
        } else {
            System.out.println("'" + input + "' was not valid. Choose" +
                " from list above, or Q or R.");
        }
    }

    private boolean userRestocked(String input) {
        return input.equals("r");
    }

    private boolean userQuits(String input) {
        return input.equals("q");
    }

    public void displayInventoryAndMenu() {
        displayInventory();
        drinks.displayDrinkMenu();
    }

    private void displayInventory() {
        System.out.println("\nIngredient Inventory:\n");
        for (Ingredient ingredient : ingredientList) {
            System.out.println(ingredient.getName().displayName() + ", " + ingredient.showStock());
        }
    }

    public void makeDrink(Drink drink) {
        dispenseDrink(drink);
        drinks.updateMakeable();
        displayInventoryAndMenu();
    }

    private void dispenseDrink(Drink drink) {
        if (drink.getMakeable()) {
            System.out.println("Dispensing: " + drink.getName() + "\n");
            for (Ingredient ingredient : ingredientList) {
                Recipe recipe = drink.getRecipe();
                if (recipe.hasIngredient(ingredient)) {
                    ingredient.reduceInventory(recipe);
                }
            }
        } else {
            System.out.println("Out of stock: " + drink.getName() + "\n");
        }
    }

    public void restockIngredients() {
        for (Ingredient ingredient : ingredientList) {
            ingredient.refillStockFor();
        }
        drinks.updateMakeable();
        displayInventoryAndMenu();
    }

}

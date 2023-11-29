package lld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

interface Drink {
    int getPrice();

    String getName();

    int getQuantityLeft();

    void serveDrink();
}

class ServeDrinkSummary {
    Drink servedDrink;
    int change;
}

class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

class InsufficientMoneyException extends Exception {
    public InsufficientMoneyException() {
        super();
    }
}


class VendingMachine {
    HashMap<Integer, Drink> drinkRelatedToButton = new HashMap<>();

    void registerDrink(int buttonIdx, Drink drink) {
        drinkRelatedToButton.put(buttonIdx, drink);
    }

    ServeDrinkSummary dispatch(int buttonPressed, int money) throws OutOfStockException, InsufficientMoneyException {
        ServeDrinkSummary serveDrinkSummary = new ServeDrinkSummary();
        serveDrinkSummary.servedDrink = drinkRelatedToButton.get(buttonPressed);
        serveDrinkSummary.change = money - serveDrinkSummary.servedDrink.getPrice();

        if (serveDrinkSummary.servedDrink.getQuantityLeft() == 0) {
            throw new OutOfStockException(serveDrinkSummary.servedDrink.getName() + " is out of stock");
        }

        if (serveDrinkSummary.change < 0) {
            throw new InsufficientMoneyException();
        }
        return serveDrinkSummary;
    }
}

class Solution {

    public static void main(String[] args) throws IOException {
        String arr[];
        int quantity[] = new int[3], pricePerUnit[] = new int[3], buttonAssigned[] = new int[3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        arr = br.readLine().split(" ");
        for (int i = 0; i < 3; i++) {
            quantity[i] = Integer.parseInt(arr[i]);
        }

        arr = br.readLine().split(" ");
        for (int i = 0; i < 3; i++) {
            pricePerUnit[i] = Integer.parseInt(arr[i]);
        }

        arr = br.readLine().split(" ");
        for (int i = 0; i < 3; i++) {
            buttonAssigned[i] = Integer.parseInt(arr[i]);
        }

        VendingMachine vendingMachine = new VendingMachine();
        Drink coke = new Coke(pricePerUnit[0], "Coke", quantity[0]);
        Drink fanta = new Fanta(pricePerUnit[1], "Fanta", quantity[1]);
        Drink sprite = new Sprite(pricePerUnit[2], "Sprite", quantity[2]);
        vendingMachine.registerDrink(buttonAssigned[0], coke);
        vendingMachine.registerDrink(buttonAssigned[1], fanta);
        vendingMachine.registerDrink(buttonAssigned[2], sprite);
        out.println("Vending machine set up");

        int totalNumberOfRequests = Integer.parseInt(br.readLine().trim());
        while (totalNumberOfRequests-- > 0) {
            arr = br.readLine().split(" ");
            int buttonPressed = Integer.parseInt(arr[0]),
                    money = Integer.parseInt(arr[1]);
            try {
                ServeDrinkSummary serveDrinkSummary = vendingMachine.dispatch(buttonPressed, money);
                serveDrinkSummary.servedDrink.serveDrink();
                out.println(serveDrinkSummary.servedDrink.getName() + " " + serveDrinkSummary.change);
            } catch (OutOfStockException e) {
                out.println(e.getMessage());
            } catch (InsufficientMoneyException e) {
                out.println("Insufficient money");
            }
        }

        out.flush();
        out.close();
    }
}

class Coke implements Drink {
    private int price;
    private String name;
    private int quantityLeft;

    public Coke(int price, String name, int quantityLeft) {
        this.price = price;
        this.name = name;
        this.quantityLeft = quantityLeft;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantityLeft() {
        return this.quantityLeft;
    }

    public void serveDrink() {
        if (this.quantityLeft > 0)
            this.quantityLeft -= 1;
    }
}

class Fanta implements Drink {
    private int price;
    private String name;
    private int quantityLeft;

    public Fanta(int price, String name, int quantityLeft) {
        this.price = price;
        this.name = name;
        this.quantityLeft = quantityLeft;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantityLeft() {
        return this.quantityLeft;
    }

    public void serveDrink() {
        if (this.quantityLeft > 0)
            this.quantityLeft -= 1;
    }
}

class Sprite implements Drink {
    private int price;
    private String name;
    private int quantityLeft;

    public Sprite(int price, String name, int quantityLeft) {
        this.price = price;
        this.name = name;
        this.quantityLeft = quantityLeft;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantityLeft() {
        return this.quantityLeft;
    }

    public void serveDrink() {
        if (this.quantityLeft > 0)
            this.quantityLeft -= 1;
    }
}
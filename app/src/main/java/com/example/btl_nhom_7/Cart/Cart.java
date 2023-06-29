package com.example.btl_nhom_7.Cart;

import android.util.Log;

import com.example.btl_nhom_7.Motor.Motor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart {
    public static final int LIMIT_PRODUCT = 100;
    private static final String TAG = "CART";
    private static Map<Motor, Integer> listItem = new HashMap<>();
    private static Cart INSTANCE = new Cart();

    private Cart() {
    }

    public static Cart getInstance() {
        return INSTANCE;
    }

    public static void addItemToCart(Motor item, int quantity) {

        Log.d(TAG, "addItemToCart: " + listItem.get(item));

        if (listItem.get(item) != null) {
            int i = listItem.get(item);
            listItem.put(item, i + quantity);
        } else {
            listItem.put(item, quantity);
        }

        printCart();
    }

    public static void clearCart() {
        listItem.clear();
    }

    public static int totalPrice() {
        AtomicInteger total = new AtomicInteger();

        listItem.forEach((item, quantity) -> {
            total.set((int) (total.get() + item.getPrice() * quantity));
        });

        return total.get();
    }

    public static int countTotalQuantity() {
        AtomicInteger count = new AtomicInteger();

        listItem.forEach((item, quantity) -> {
            count.set(count.get() + quantity);
        });

        return count.get();
    }

    public static void removeItemFromCart(Motor item) {
        listItem.remove(item);
    }

    public static void increaseQuantityItemInCart(Motor item) {
        if (listItem.get(item) != null) {
            int i = listItem.get(item);
            if(countTotalQuantity() < LIMIT_PRODUCT) {
                listItem.put(item, i + 1);
            }
        }
    }

    public static void decreaseQuantityItemInCart(Motor item) {
        if (listItem.containsKey(item)) {
            int i = listItem.get(item);
            if(i > 1) {
                listItem.put(item, i - 1);
            }
        }
    }

    public static Map getCartItem() {
        return listItem;
    }

    public static void printCart() {
        Log.i(TAG, "total sp:  " + countTotalQuantity() + " ---------------------------------- ");
        listItem.forEach((item, quantity) -> {
            Log.i(TAG, "Item : " + item.toString());
            Log.i(TAG, "Quantity : " + quantity);
        });
    }


}

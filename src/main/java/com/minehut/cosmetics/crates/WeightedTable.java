package com.minehut.cosmetics.crates;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WeightedTable<T> {

    private final HashMap<T, Integer> table = new HashMap<>();
    private int totalWeight = 0;

    public T roll() {
        if (table.isEmpty()) {
            throw new IllegalStateException("Cannot roll for items on an empty table.");
        }

        int currentWeight = 0;
        int roll = (int) (Math.random() * totalWeight);

        for (final Map.Entry<T, Integer> entry : table.entrySet()) {
            currentWeight += entry.getValue();
            if (roll <= currentWeight) return entry.getKey();
        }

        throw new IllegalStateException("Failed to roll a valid number.");
    }

    public void registerItem(T item, int weight) {
        table.put(item, weight);
        totalWeight += weight;
    }
}
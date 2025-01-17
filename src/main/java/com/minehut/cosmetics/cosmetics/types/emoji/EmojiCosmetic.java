package com.minehut.cosmetics.cosmetics.types.emoji;

import com.minehut.cosmetics.cosmetics.Cosmetic;
import com.minehut.cosmetics.cosmetics.CosmeticCategory;
import com.minehut.cosmetics.util.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class EmojiCosmetic extends Cosmetic {

    protected EmojiCosmetic(String id) {
        super(id, CosmeticCategory.EMOJI);
    }

    @Override
    public @NotNull ItemStack menuIcon() {
        return ItemBuilder.of(Material.PAPER).display(component()).build();
    }

    public abstract @NotNull String keyword();

    public abstract @NotNull Component component();

    @Override
    public int salvageAmount() {
        return 0;
    }
}
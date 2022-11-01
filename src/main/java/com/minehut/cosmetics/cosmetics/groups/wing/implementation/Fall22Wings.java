package com.minehut.cosmetics.cosmetics.groups.wing.implementation;

import com.minehut.cosmetics.cosmetics.CosmeticCategory;
import com.minehut.cosmetics.cosmetics.CosmeticCollection;
import com.minehut.cosmetics.cosmetics.CosmeticPermission;
import com.minehut.cosmetics.cosmetics.groups.wing.Wing;
import com.minehut.cosmetics.cosmetics.groups.wing.WingCosmetic;
import com.minehut.cosmetics.ui.model.Model;
import com.minehut.cosmetics.util.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class Fall22Wings extends WingCosmetic {

    private static final Component NAME = Component.text("Wings of Autumn")
            .color(NamedTextColor.AQUA)
            .decoration(TextDecoration.ITALIC, false);

    private static final Supplier<ItemStack> ITEM = ItemBuilder.of(Material.SCUTE)
            .display(NAME)
            .lore(
                    Component.empty(),
                    CosmeticCollection.FALL_22.tag(),
                    Component.empty()
            )
            .modelData(Model.WING.FALL_22)
            .supplier();

    public Fall22Wings() {
        super(Wing.FALL_22.name(),
                NAME,
                CosmeticPermission.hasPurchased(CosmeticCategory.WING.name(), Wing.FALL_22.name()),
                p -> ITEM.get());
    }

    @Override
    public ItemStack menuIcon() {
        return ITEM.get();
    }
}
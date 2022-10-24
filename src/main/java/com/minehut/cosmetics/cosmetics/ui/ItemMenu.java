package com.minehut.cosmetics.cosmetics.ui;

import com.minehut.cosmetics.cosmetics.CosmeticCategory;
import com.minehut.cosmetics.cosmetics.equipment.ClickHandler;
import com.minehut.cosmetics.cosmetics.equipment.CosmeticSlot;
import com.minehut.cosmetics.cosmetics.groups.item.Item;
import com.minehut.cosmetics.util.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemMenu extends CosmeticSubMenu {

    public static final ItemStack ICON = ItemBuilder.of(Material.IRON_SWORD)
            .flags(ItemFlag.HIDE_ATTRIBUTES)
            .display(Component.text("Equipment", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false))
            .lore(
                    Component.empty(),
                    Component.text("Equipment Skins to hold in your hand!", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, true),
                    Component.empty()
            )
            .build();


    public ItemMenu(Player player) {
        super(CosmeticCategory.ITEM, player, List.of(Item.values()), ClickHandler.HANDED);
    }
}

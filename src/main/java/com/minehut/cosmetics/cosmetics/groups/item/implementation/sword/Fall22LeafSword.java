package com.minehut.cosmetics.cosmetics.groups.item.implementation.sword;

import com.minehut.cosmetics.cosmetics.CosmeticCategory;
import com.minehut.cosmetics.cosmetics.CosmeticCollection;
import com.minehut.cosmetics.cosmetics.CosmeticPermission;
import com.minehut.cosmetics.cosmetics.groups.companion.Companion;
import com.minehut.cosmetics.cosmetics.groups.hat.Hat;
import com.minehut.cosmetics.cosmetics.groups.item.Item;
import com.minehut.cosmetics.cosmetics.groups.item.ItemCosmetic;
import com.minehut.cosmetics.cosmetics.groups.wing.Wing;
import com.minehut.cosmetics.ui.model.Model;
import com.minehut.cosmetics.util.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class Fall22LeafSword extends ItemCosmetic {

    public static final Component NAME = Component.text("Fallbringer")
            .color(NamedTextColor.GOLD)
            .decoration(TextDecoration.ITALIC, false);

    public static final Supplier<ItemStack> ITEM = ItemBuilder.of(Material.DIAMOND_SWORD)
            .display(NAME)
            .lore(
                    Component.empty(),
                    CosmeticCollection.FALL_22.tag(),
                    Component.empty()
            )
            .flags(ItemFlag.HIDE_ATTRIBUTES)
            .modelData(Model.Item.Sword.FALL_22_LEAF_SWORD)
            .supplier();

    public static final Function<Player, CompletableFuture<Boolean>> PERMISSION = CosmeticPermission.all(List.of(
            CosmeticPermission.hasPurchased(CosmeticCategory.ITEM.name(), Item.FALL_22_SWORD.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.ITEM.name(), Item.FALL_22_SHIELD.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.ITEM.name(), Item.FALL_22_PICKAXE.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.ITEM.name(), Item.FALL_22_AXE.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.ITEM.name(), Item.FALL_22_SHOVEL.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.ITEM.name(), Item.FALL_22_ARROW.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.ITEM.name(), Item.FALL_22_BOW.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.HAT.name(), Hat.FALL_22.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.WING.name(), Wing.FALL_22.name()),
            CosmeticPermission.hasPurchased(CosmeticCategory.COMPANION.name(), Companion.LATTE_KUN.name())
    ));

    public Fall22LeafSword() {
        super(
                Item.FALL_22_LEAF_SWORD.name(),
                NAME,
                PERMISSION
        );
    }

    @Override
    public ItemStack item() {
        return ITEM.get();
    }
}
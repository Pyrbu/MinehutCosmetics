package com.minehut.cosmetics.cosmetics.types.wing;

import com.minehut.cosmetics.cosmetics.Cosmetic;
import com.minehut.cosmetics.cosmetics.CosmeticCategory;
import com.minehut.cosmetics.cosmetics.properties.Equippable;
import com.minehut.cosmetics.cosmetics.properties.Tickable;
import com.minehut.cosmetics.util.EntityUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pose;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.Set;

public abstract class WingCosmetic extends Cosmetic implements Equippable, Tickable {

    private static final Set<Pose> blacklisted = Set.of(Pose.SWIMMING, Pose.SLEEPING, Pose.DYING, Pose.FALL_FLYING, Pose.SPIN_ATTACK);
    private ArmorStand wings;
    private Entity base;

    private boolean equipped = false;
    private boolean hidden = true;

    protected WingCosmetic(String id) {
        super(id, CosmeticCategory.WING);
    }

    @Override
    public void equip() {
        if (equipped) return;
        equipped = true;
        show();
    }

    @Override
    public void unequip() {
        if (!equipped) return;
        equipped = false;
        hide();
    }


    /**
     * Get the item to be used for the wings
     *
     * @return the wing item
     */
    public ItemStack getWingItem() {
        return menuIcon();
    }

    public void hide() {
        if (hidden) return;
        hidden = true;
        wings().ifPresent(Entity::remove);
        base().ifPresent(Entity::remove);
    }

    public void show() {
        if (!hidden) return;
        hidden = false;

        player().ifPresent(player -> {
            final Location spawn = player.getLocation();
            spawn.setYaw(0);
            spawn.setPitch(0);
            this.wings = EntityUtil.spawnModelStand(player.getLocation(), wings -> {
                wings.setItem(EquipmentSlot.HEAD, menuIcon());
                wings.setVisible(true);
            });

            this.base = EntityUtil.spawnCosmeticEntity(player.getLocation(), AreaEffectCloud.class, cloud -> {
                cloud.clearCustomEffects();
                cloud.setRadius(0);
                cloud.setParticle(Particle.BLOCK_CRACK, Bukkit.createBlockData(Material.AIR));
                cloud.setTicksLived(Integer.MAX_VALUE);
            });

            base.addPassenger(wings);
            player.addPassenger(base);
        });
    }


    float lastAngle = 0;

    @Override
    public void tick() {
        if (!equipped) return;

        player().ifPresent(player -> {

            final boolean shouldHide = blacklisted.contains(player.getPose());

            if (shouldHide) {
                hide();
                player.sendActionBar(Component.text("Your wings have been hidden.").color(NamedTextColor.GRAY));
                return;
            }

            if (hidden) {
                show();
                player.sendActionBar(Component.text("Your wings have been un-hidden").color(NamedTextColor.GRAY));
            }


            wings().ifPresent(wing -> {
                final float rawAngle = player.getEyeLocation().getYaw();

                if (Math.abs(lastAngle - (rawAngle + 180)) >= 10) {
                    wings.setRotation(player.getEyeLocation().getYaw(), 0);
                    lastAngle = rawAngle + 180;
                }
            });
        });
    }

    public Optional<ArmorStand> wings() {
        return Optional.ofNullable(wings);
    }

    public Optional<Entity> base() {
        return Optional.ofNullable(base);
    }
}

package com.minehut.cosmetics.cosmetics.types.companion;

import com.minehut.cosmetics.cosmetics.CosmeticSupplier;
import com.minehut.cosmetics.cosmetics.collections.betacrate.DragonEggCompanion;
import com.minehut.cosmetics.cosmetics.collections.betacrate.UfoCowCompanion;
import com.minehut.cosmetics.cosmetics.collections.betacrate.YoungDragonCompanion;
import com.minehut.cosmetics.cosmetics.collections.general.BearCompanion;
import com.minehut.cosmetics.cosmetics.collections.beta.CompieCompanion;
import com.minehut.cosmetics.cosmetics.collections.halloween2022.GhostCompanion;
import com.minehut.cosmetics.cosmetics.collections.general.GoldFishCompanion;
import com.minehut.cosmetics.cosmetics.collections.general.GreenTurtleCompanion;
import com.minehut.cosmetics.cosmetics.collections.general.KittenCompanion;
import com.minehut.cosmetics.cosmetics.collections.autumn2022.LatteCompanion;
import com.minehut.cosmetics.cosmetics.collections.dev.MeFollower;
import com.minehut.cosmetics.cosmetics.collections.general.RedRobinCompanion;
import com.minehut.cosmetics.cosmetics.collections.netflix2022.WendellAndWildCompanion;
import com.minehut.cosmetics.cosmetics.types.follower.FollowerCosmetic;

import java.util.function.Supplier;

public enum Companion implements CosmeticSupplier<FollowerCosmetic> {
    EXPLORER(CompieCompanion::new),
    LATTE_KUN(LatteCompanion::new),
    ME_BUDDY(MeFollower::new),
    WENDELL_AND_WILD(WendellAndWildCompanion::new),
    GHOST(GhostCompanion::new),
    GREEN_TURTLE(GreenTurtleCompanion::new),
    GOLD_FISH(GoldFishCompanion::new),
    KITTEN(KittenCompanion::new),
    BEAR(BearCompanion::new),
    RED_ROBIN(RedRobinCompanion::new),
    DRAGON_EGG(DragonEggCompanion::new),
    YOUNG_DRAGON(YoungDragonCompanion::new),
    UFO_COW(UfoCowCompanion::new);

    private final Supplier<FollowerCosmetic> supplier;

    Companion(final Supplier<FollowerCosmetic> supplier) {
        this.supplier = supplier;
    }

    @Override
    public FollowerCosmetic get() {
        return supplier.get();
    }
}

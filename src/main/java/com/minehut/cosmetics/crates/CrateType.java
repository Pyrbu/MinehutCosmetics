package com.minehut.cosmetics.crates;

import com.minehut.cosmetics.cosmetics.CosmeticSupplier;
import com.minehut.cosmetics.crates.impl.LegendaryCrate;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum CrateType implements CosmeticSupplier<Crate> {
    LEGENDARY(LegendaryCrate::new);

    private final Supplier<Crate> supplier;

    CrateType(Supplier<Crate> supplier) {
        this.supplier = supplier;
    }

    @Override
    public @NotNull Crate get() {
        return supplier.get();
    }
}
package com.minehut.cosmetics.config;

import com.minehut.cosmetics.util.EnumUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Optional;

public class Config {

    private String apiUrl = "https://api.minehut.com";
    private String apiSecret = "";
    private Mode mode = Mode.PLAYER_SERVER;
    private Location crateLocation = null;

    private final CompanionConfig companion;

    private final Plugin plugin;

    public Config(Plugin plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        reload();

        this.companion = new CompanionConfig(plugin);
    }

    public void load() {
        final FileConfiguration pluginConfig = plugin.getConfig();

        // load the mode we're using for the plugin
        EnumUtil.valueOfSafe(Mode.class, pluginConfig.getString("mode")).ifPresent(newMode -> this.mode = newMode);

        switch (mode) {
            // if we're using the lobby, try to load the socket auth config as well
            case LOBBY -> {
                final YamlConfiguration lobbyConfig = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder().getParent(), "Lobby/socketauth.yml"));
                Optional.ofNullable(lobbyConfig.getString("api.url")).ifPresent(url -> this.apiUrl = url);
                Optional.ofNullable(lobbyConfig.getString("api.auth")).ifPresent(secret -> this.apiSecret = secret);

                String worldName = pluginConfig.getString("crateLocation.name", "lobby");
                double x = pluginConfig.getDouble("crateLocation.x", 0);
                double y = pluginConfig.getDouble("crateLocation.y", 0);
                double z = pluginConfig.getDouble("crateLocation.z", 0);
                float yaw = (float) pluginConfig.getDouble("crateLocation.yaw", 0);


                final World world = Bukkit.getWorld(worldName);
                this.crateLocation = new Location(world, x, y, z, yaw, 0);
            }
            case PLAYER_SERVER -> {
                Optional.ofNullable(pluginConfig.getString("apiUrl")).ifPresent(newUrl -> this.apiUrl = newUrl);
            }
        }
    }

    /**
     * Get the operating mode for the server, if the configuration is malformed, falls
     * back to using {@link com.minehut.cosmetics.config.Mode#PLAYER_SERVER}
     *
     * @return the operating mode to use for the plugin
     */
    public Mode mode() {
        return mode;
    }

    public CompanionConfig companion() {
        return companion;
    }

    /**
     * Return the URL endpoint for fetching cosmetic information.
     *
     * @return the api url
     */
    public String apiUrl() {
        return apiUrl;
    }

    public String apiSecret() {
        return apiSecret;
    }

    public Location crateLocation() {
        return crateLocation;
    }

    public void reload() {
        plugin.reloadConfig();
        load();
    }
}

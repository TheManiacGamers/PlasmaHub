package me.yeroc.PlasmaHub.managers;

import me.yeroc.PlasmaHub.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Corey on 17/12/2018.
 */
public class PlayerData {
    private static File dataFolder;
    private static File playerDataFolder;

    public static void setDataFolder() {
        dataFolder = new File(Main.plugin.getDataFolder().toString());
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
            Main.log("Success. Created the PlasmaHub directory.");
        } else {
            Main.log("Success. Loaded the PlasmaHub directory. ");
        }
    }

    public static void setFolderPath(File dataFolder, String folderName) {
        setDataFolder();
        playerDataFolder = new File(dataFolder + File.separator + folderName);
        if (!playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
            Main.log("Success. Created the PlayerData directory.");
        } else {
            Main.log("Success. Loaded the PlayerData directory.");
        }
    }

    public static boolean hasYaml(UUID uuid) {
        return getYaml(uuid) != null;
    }

    public static YamlConfiguration getYaml(UUID uuid) {
        File file = new File(playerDataFolder, uuid.toString() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        return !file.exists() ? null : config;
    }

    public static YamlConfiguration createYaml(UUID uuid) {
        File file = new File(playerDataFolder, uuid.toString() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        saveYaml(uuid, config);

        return config;
    }

    public static boolean saveYaml(UUID uuid, YamlConfiguration config) {
        File file = new File(playerDataFolder, uuid.toString() + ".yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
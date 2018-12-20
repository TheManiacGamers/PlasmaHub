package me.yeroc.PlasmaHub.managers;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.serverselector.ServerSelector;
import me.yeroc.PlasmaHub.utils.API;
import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by creyn63 on 5/07/2016.
 */
public class Configs {
    private static Configs instance = new Configs();
    private Main plugin;
    private Plugin p;
    private FileConfiguration config;
    private File cFile;
    public FileConfiguration messagesConfig;
    public File messagesFile;


    private Configs() {
    }

    public static Configs getInstance() {
        return instance;
    }

    public void setup(Plugin p) {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        cFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PlasmaHub").getDataFolder(), "config.yml");
        if (!cFile.exists()) {
            try {
                cFile.createNewFile();
                Main.log("Creating config.yml file..");
            } catch (IOException ex) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create config.yml!");
                return;
            }
            Main.log("config.yml file has been created.");
        }
        config = YamlConfiguration.loadConfiguration(cFile);
        Main.log("Loaded the config.yml file successfully!");

        messagesFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PlasmaHub").getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            try {
                messagesFile.createNewFile();
                Main.log("Creating messages.yml file..");
            } catch (IOException ex) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create messages.yml!");
                return;
            }
            Main.log("messages.yml file has been created.");
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        Main.log("Loaded the messages.yml file successfully!");

    }

    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();
    private Configs configs = Configs.getInstance();
    private ServerSelector serverSelector = ServerSelector.getInstance();
    private RewardsManager rewards = RewardsManager.getInstance();
    private GemsManager gems = GemsManager.getInstance();

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(cFile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public FileConfiguration getMessages() {
        return messagesConfig;
    }

    public void saveMessages() {
        try {
            messagesConfig.save(messagesFile);
            Main.log("messages.yml saved.");
        } catch (IOException ex) {
            Main.log("Error saving messages. Check console.");
        }
    }

    public void reloadMessages() {
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cFile);
    }

    public PluginDescriptionFile getDesc() {
        return p.getDescription();
    }

}

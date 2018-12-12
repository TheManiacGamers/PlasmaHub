package me.yeroc.PlasmaHub;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.managers.Configs;
import me.yeroc.PlasmaHub.managers.Strings;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 9/12/2018.
 */
public class AutoBroadcast extends BukkitRunnable {
    private static AutoBroadcast instance = new AutoBroadcast();
    private Main plugin;


    public AutoBroadcast() {
    }

    public static AutoBroadcast getInstance() {
        return instance;
    }

    private Configs configs = Configs.getInstance();
    private Strings strings = Strings.getInstance();
    public String default1 = (ChatColor.RED + "THIS IS A DEFAULT MESSAGE");
    public String default2 = (ChatColor.RED + "THIS IS THE SECOND DEFAULT MESSAGE");
    public String default3 = (ChatColor.RED + "THIS IS ANOTHER DEFAULT MESSAGE");

    public void setDefaults() {
//        List<String> list = new ArrayList<>();
        Main.log("Setting default AutoBroadcast messages");
        configs.getConfig().createSection("AutoBroadcast.Messages");
        configs.getConfig().getStringList("AutoBroadcast.Messages").add(default1);
        configs.getConfig().getStringList("AutoBroadcast.Messages").add(default2);
        configs.getConfig().getStringList("AutoBroadcast.Messages").add(default3);
        configs.saveConfig();
        Main.log("Default AutoBroadcast messages have been set");
//        Main.autoBroadcastMessages.add(default1);
//        Main.autoBroadcastMessages.add(default2);
//        Main.autoBroadcastMessages.add(default3);
    }

    public void loadMessages() {
        if (configs.getConfig().get("AutoBroadcast.Messages") == null) {
            setDefaults();
        }
        Main.log("Loading AutoBroadcast messages");
        Main.log("Messages to load: " + configs.getConfig().getStringList("AutoBroadcast.Messages").size());
        for (int i = 0; i < configs.getConfig().getStringList("AutoBroadcast.Messages").size(); ) {
            for (String s : configs.getConfig().getStringList("AutoBroadcast.Messages")) {
                if (s.contains("\'")) {
                    Main.log("Can not load: " + s + " because there is an illegal character in the messages.yml file.");
                    return;
                }
                Main.autoBroadcastMessages.add(s);
                Main.log(strings.getMessage("autoBroadcastMessages") + s);
            }
            if (Main.autoBroadcastMessages.size() < configs.getConfig().getStringList("AutoBroadcast.Messages").size()) {
                i++;
            } else {
                Main.log("Completed loading AutoBroadcast messages.");
                Main.log(strings.getMessage("autoBroadcastMessagesLoaded") + Main.autoBroadcastMessages.size());
                return;
            }
        }
        Bukkit.broadcastMessage("Error loading AutoBroadcast Messages.");
    }

    private int i = 0;
    private int current = -1;

    private boolean firstTime = true;

    public String getNextMessageAndIncrement() {
        current++;
        if (current == Main.autoBroadcastMessages.size()) current = 0;
        return Main.autoBroadcastMessages.get(current);
    }

    private int time = 0;

    public void run() {
        if (Bukkit.getOnlinePlayers().size() != 0) {
            if (Main.autoBroadcastMessages.size() == 0) {
                loadMessages();
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('§', strings.getMessage("autoBroadcastPrefix") + getNextMessageAndIncrement()));
                time = 0;
            }
        }
    }

}

package me.yeroc.PlasmaHub.managers;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Corey on 17/12/2018.
 */

public class PlayerConfig {
    private File cfile;
    private FileConfiguration config;
    private File folder;
    private File df;

    private Main plugin;

    private static PlayerConfig instance = new PlayerConfig();

    public PlayerConfig(Main plugin) {
        this.plugin = plugin;
    }

    private PlayerConfig() {
    }

    public static PlayerConfig getInstance() {
        return instance;
    }

    public void initialize() {
        folder = new File(Main.plugin.getDataFolder(), "PlayerData" + File.separator);
        df = Main.plugin.getDataFolder();
        Main.log("PlayerConfig initialized in PlayerConfig.");
    }

    private void check(Player p) {
        cfile = new File(df, "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        if (!df.exists()) df.mkdir();
        if (!cfile.exists()) {
            create(p);
        }
        config = YamlConfiguration.loadConfiguration(cfile);
        if (config.get("Information.Name") == null || (config.getString("Information.Name").equalsIgnoreCase(p.getName()))) {
            config.set("Information.Name", p.getName());
        }
        if (config.get("Statistics.Kills") == null) {
            config.set("Statistics.Kills", 0);
        }
        if (config.get("Statistics.Deaths") == null) {
            config.set("Statistics.Deaths", 0);
        }
        if (config.get("Statistics.Joins") == null) {
            config.set("Statistics.Joins", 1);
        }
        if (config.get("Statistics.Gems") == null) {
            config.set("Statistics.Gems", 10);
        }
        if (config.get("Statistics.Time Online") == null) {
            config.set("Statistics.Time Online", 0);
        }
        if (config.get("Statistics.PVPLevel") == null) {
            config.set("Statistics.PVPLevel", 0);
        }
        if (config.get("Statistics.PVPExp") == null) {
            config.set("Statistics.PVPExp", 50);
        }
        if (config.get("Statistics.Current_Killstreak") == null) {
            config.set("Statistics.Current_Killstreak", 0);
        }
        if (config.get("Statistics.Longest_Killstreak") == null) {
            config.set("Statistics.Longest_Killstreak", 0);
        }
        if (config.get("Statistics.Current_Deathstreak") == null) {
            config.set("Statistics.Current_Deathstreak", 0);
        }
        if (config.get("Completed.Maze") == null) {
            config.set("Completed.Maze", "no");
        }
        if (config.get("Completed.Parkour") == null) {
            config.set("Completed.Parkour", "no");
        }
        if (config.get("Toggled.Bar") == null) {
            config.set("Toggled.Bar", "yes");
        }
    }

    public void create(Player p) {
        cfile = new File(df, "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        if (!df.exists()) df.mkdir();
        if (!cfile.exists()) {
            try {
                cfile.createNewFile();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + cfile.getName() + "!");
            }
        }
        config.set("Information.Name", p.getName());
        config.set("Statistics.Joins", 1);
        config.set("Statistics.Kills", 0);
        config.set("Statistics.Deaths", 0);
        config.set("Statistics.Gems", 10);
        config.set("Statistics.Time Online", 0);
        config.set("Completed.Parkour", "no");
        config.set("Completed.Maze", "no");
        config.set("Statistics.Current_Killstreak", 0);
        config.set("Statistics.Longest_Killstreak", 0);
        config.set("Statistics.Current_Deathstreak", 0);
        config.set("DailyRewards.Claimed", "no");
        Main.pfm_uuid.put(p.getUniqueId(), p.getUniqueId().toString());
        Main.pfm_name.put(p.getUniqueId(), p.getName());
        Main.pfm_joins.put(p.getUniqueId(), 1);
        Main.pfm_kills.put(p.getUniqueId(), 0);
        Main.pfm_deaths.put(p.getUniqueId(), 0);
        Main.pfm_totalGems.put(p.getUniqueId(), 10);
        Main.pfm_timeOnline.put(p.getUniqueId(), 0);
        Main.pfm_completedMaze.put(p.getUniqueId(), "no");
        Main.pfm_completedParkour.put(p.getUniqueId(), "no");
        Main.pfm_killstreak.put(p.getUniqueId(), 0);
        Main.pfm_longestKillstreak.put(p.getUniqueId(), 0);
        Main.pfm_deathstreak.put(p.getUniqueId(), 0);
        p.setTotalExperience(p.getExpToLevel() + 1);
        save(p);
    }

    public File getfolder() {
        return folder;
    }

    public File getfile(Player p) {
        cfile = new File(df, "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        return cfile;
    }

    public void load(Player p) {
        cfile = new File(df, "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(cfile);
        Main.pfm_name.put(p.getUniqueId(), config.getString("Information.Name"));
        Main.pfm_joins.put(p.getUniqueId(), config.getInt("Statistics.Joins"));
        Main.pfm_kills.put(p.getUniqueId(), config.getInt("Statistics.Kills"));
        Main.pfm_deaths.put(p.getUniqueId(), config.getInt("Statistics.Deaths"));
        Main.pfm_totalGems.put(p.getUniqueId(), config.getInt("Statistics.Gems"));
        Main.pfm_timeOnline.put(p.getUniqueId(), config.getInt("Statistics.Time Online"));
        Main.pfm_pvpLevel.put(p.getUniqueId(), config.getInt("Statistics.PVPLevel"));
        Main.pfm_pvpExp.put(p.getUniqueId(), config.getInt("Statistics.PVPExp"));
        Main.pfm_killstreak.put(p.getUniqueId(), config.getInt("Statistics.Current_Killstreak"));
        Main.pfm_longestKillstreak.put(p.getUniqueId(), config.getInt("Statistics.Longest_Killstreak"));
        Main.pfm_deathstreak.put(p.getUniqueId(), config.getInt("Statistics.Current_Deathstreak"));
        Main.pfm_completedMaze.put(p.getUniqueId(), config.getString("Completed.Maze"));
        Main.pfm_completedParkour.put(p.getUniqueId(), config.getString("Completed.Parkour"));
        Main.barEnabled.put(p.getUniqueId(), config.getString("Toggled.Bar"));
        Main.dailyRewards.put(p.getUniqueId(), config.getString("DailyRewards.Claimed"));
    }

    public FileConfiguration get(Player p) {
        cfile = new File(df, "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(cfile);
        return config;
    }

    public void save(Player p) {
        config.set("Information.Name", p.getName());
        config.set("Statistics.Joins", Main.pfm_joins.get(p.getUniqueId()));
        config.set("Statistics.Kills", Main.pfm_kills.get(p.getUniqueId()));
        config.set("Statistics.Deaths", Main.pfm_deaths.get(p.getUniqueId()));
        config.set("Statistics.Gems", Main.pfm_totalGems.get(p.getUniqueId()));
        config.set("Statistics.Time Online", Main.pfm_timeOnline.get(p.getUniqueId()));
        config.set("Statistics.PVPLevel", Main.pfm_pvpLevel.get(p.getUniqueId()));
        config.set("Statistics.PVPExp", Main.pfm_pvpExp.get(p.getUniqueId()));
        config.set("Completed.Maze", Main.pfm_completedMaze.get(p.getUniqueId()));
        config.set("Completed.Parkour", Main.pfm_completedParkour.get(p.getUniqueId()));
        config.set("Toggled.Bar", Main.barEnabled.get(p.getUniqueId()));
        config.set("Statistics.Current_Killstreak", Main.pfm_killstreak.get(p.getUniqueId()));
        config.set("Statistics.Longest_Killstreak", Main.pfm_longestKillstreak.get(p.getUniqueId()));
        config.set("Statistics.Current_Deathstreak", Main.pfm_deathstreak.get(p.getUniqueId()));
        config.set("DailyRewards.Claimed", Main.dailyRewards.get(p.getUniqueId()));
//        cfile = new File(Bukkit.getPluginManager().getPlugin("PlasmaHub").getDataFolder() + "PlayerData" + File.separator);
        config = YamlConfiguration.loadConfiguration(cfile);
        File file = new File(Main.plugin.getDataFolder(), File.separator + "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

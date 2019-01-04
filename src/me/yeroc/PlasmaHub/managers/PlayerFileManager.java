package me.yeroc.PlasmaHub.managers;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.serverselector.ServerSelector;
import me.yeroc.PlasmaHub.utils.API;
import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

/**
 * Created by Corey on 1/12/2018.
 */
public class PlayerFileManager {

    private Main plugin;

    private static PlayerFileManager instance = new PlayerFileManager();

    public PlayerFileManager(Main plugin) {
        this.plugin = plugin;
    }

    private PlayerFileManager() {
    }


    public static PlayerFileManager getInstance() {
        return instance;
    }

    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();
    private ServerSelector serverSelector = ServerSelector.getInstance();
    private RewardsManager rewardsManager = RewardsManager.getInstance();
    private GemsManager gemsManager = GemsManager.getInstance();

    private File cfile;
    private FileConfiguration config;

    private String statistics = "Statistics.";
    private String information = "Information.";

    public void addJoins(Player p, int i) {
        if (Main.pfm_joins.get(p.getUniqueId()) == null) {
            Main.pfm_joins.put(p.getUniqueId(), 1);
        }
        int oldJoins = Main.pfm_joins.get(p.getUniqueId());
        int newJoins = oldJoins + i;
        Main.pfm_joins.put(p.getUniqueId(), newJoins);
    }

    public int getJoins(Player p) {
        if (Main.pfm_joins.get(p.getUniqueId()) == null) {
            Main.pfm_joins.put(p.getUniqueId(), 0);
        }
        return Main.pfm_joins.get(p.getUniqueId());
    }

    public void addKills(Player p, int i) {
        if (Main.pfm_kills.get(p.getUniqueId()) == null) {
            Main.pfm_kills.put(p.getUniqueId(), 0);
        }
        int oldKills = Main.pfm_kills.get(p.getUniqueId());
        int newKills = oldKills + i;
        Main.pfm_kills.put(p.getUniqueId(), newKills);
    }

    public int getKills(Player p) {
        if (Main.pfm_kills.get(p.getUniqueId()) == null) {
            Main.pfm_kills.put(p.getUniqueId(), 0);
        }
        return Main.pfm_kills.get(p.getUniqueId());
    }

    public void addDeaths(Player p, int i) {
        if (Main.pfm_deaths.get(p.getUniqueId()) == null) {
            Main.pfm_deaths.put(p.getUniqueId(), 0);
        }
        int oldDeaths = Main.pfm_deaths.get(p.getUniqueId());
        int newDeaths = oldDeaths + i;
        Main.pfm_deaths.put(p.getUniqueId(), newDeaths);
    }

    public int getDeaths(Player p) {
        if (Main.pfm_deaths.get(p.getUniqueId()) == null) {
            Main.pfm_deaths.put(p.getUniqueId(), 0);
        }
        return Main.pfm_deaths.get(p.getUniqueId());
    }

    public int getLevel(Player p) {
        if (Main.pfm_pvpLevel.get(p.getUniqueId()) == null) {
            Main.pfm_pvpLevel.put(p.getUniqueId(), 0);
        }
        return Main.pfm_pvpLevel.get(p.getUniqueId());
    }

    public void setLevel(Player p, int i) {
        if (Main.pfm_pvpLevel.get(p.getUniqueId()) == null) {
            Main.pfm_pvpLevel.put(p.getUniqueId(), 0);
        }
        Main.pfm_pvpLevel.put(p.getUniqueId(), i);
    }

    public void addLevel(Player p, int i) {
        if (Main.pfm_pvpLevel.get(p.getUniqueId()) == null) {
            Main.pfm_pvpLevel.put(p.getUniqueId(), 0);
        }
        int oldLevel = Main.pfm_pvpLevel.get(p.getUniqueId());
        int newLevel = oldLevel + i;
        Main.pfm_pvpLevel.put(p.getUniqueId(), newLevel);
    }

    public void addExp(Player p, int i) {
        if (Main.pfm_pvpExp.get(p.getUniqueId()) == null) {
            Main.pfm_pvpExp.put(p.getUniqueId(), 50);
        }
        int oldExp = Main.pfm_pvpExp.get(p.getUniqueId());
        int newExp = oldExp + i;
        Main.pfm_pvpExp.put(p.getUniqueId(), newExp);
    }

    public int getExp(Player p) {
        if (Main.pfm_pvpExp.get(p.getUniqueId()) == null) {
            Main.pfm_pvpExp.put(p.getUniqueId(), 50);
        }
        return Main.pfm_pvpExp.get(p.getUniqueId());
    }

    public void setExp(Player p, int i) {
        if (Main.pfm_pvpExp.get(p.getUniqueId()) == null) {
            Main.pfm_pvpExp.put(p.getUniqueId(), 50);
        }
        Main.pfm_pvpExp.put(p.getUniqueId(), i);
    }

    public void takeExp(Player p, int i) {
        if (Main.pfm_pvpExp.get(p.getUniqueId()) == null) {
            Main.pfm_pvpExp.put(p.getUniqueId(), 50);
        }
        int oldExp = Main.pfm_pvpExp.get(p.getUniqueId());
        int newExp = oldExp - i;
        Main.pfm_pvpExp.put(p.getUniqueId(), newExp);
    }

    public Integer getGems(Player p) {
        if (Main.pfm_totalGems.get(p.getUniqueId()) == null) {
            Main.pfm_totalGems.put(p.getUniqueId(), 10);
        }
        return Main.pfm_totalGems.get(p.getUniqueId());
    }

    public void addGems(Player p, int i) {
        if (Main.pfm_totalGems.get(p.getUniqueId()) == null) {
            Main.pfm_totalGems.put(p.getUniqueId(), 10);
        }
        int oldGems = Main.pfm_totalGems.get(p.getUniqueId());
        int newGems = oldGems + i;
        Main.pfm_totalGems.put(p.getUniqueId(), newGems);
    }

    public void addMoney(Player p, int i) {
        Main.log(strings.getMessage("pfm_addMoneyDisabled") + p.getName() + ".");
    }

    public void addStrength(Player p, int i) {
        Main.log(strings.getMessage("pfm_addStrengthDisabled") + p.getName() + ".");
    }

    public Integer getCurrentKillstreak(Player p) {
        if (Main.pfm_killstreak.get(p.getUniqueId()) == null) {
            Main.pfm_killstreak.put(p.getUniqueId(), 0);
        }
        return Main.pfm_killstreak.get(p.getUniqueId());
    }

    public Integer getLongestKillstreak(Player p) {
        if (Main.pfm_longestKillstreak.get(p.getUniqueId()) == null) {
            Main.pfm_longestKillstreak.put(p.getUniqueId(), 0);
        }
        return Main.pfm_longestKillstreak.get(p.getUniqueId());
    }

    public void removeCurrentKillstreak(Player p) {
        Main.pfm_killstreak.put(p.getUniqueId(), 0);
    }

    public void addCurrentKillstreak(Player p, int a) {
        Main.pfm_killstreak.put(p.getUniqueId(), Main.pfm_killstreak.get(p.getUniqueId()) + a);
    }

    public void addLongestKillstreak(Player p, int a) {
        Main.pfm_longestKillstreak.put(p.getUniqueId(), Main.pfm_longestKillstreak.get(p.getUniqueId()));
    }

    public Integer getTimeOnline(Player p) {
        return Main.pfm_timeOnline.get(p.getUniqueId());
    }

    public void addDeathStreak(Player p, int a) {
        Main.pfm_deathstreak.put(p.getUniqueId(), Main.pfm_deathstreak.get(p.getUniqueId()) + 1);
    }

    public Integer getDeathStreak(Player p) {
        if (Main.pfm_deathstreak.get(p.getUniqueId()) == null) {
            Main.pfm_deathstreak.put(p.getUniqueId(), 0);
        }
        return Main.pfm_deathstreak.get(p.getUniqueId());
    }

    public void resetDailyRewards(Player p) {
        if (Main.dailyRewards.get(p.getUniqueId()) != null) {
            Main.dailyRewards.remove(p.getUniqueId());
        }
    }

    public void removeDeathStreak(Player p) {
        if (Main.pfm_deathstreak.get(p.getUniqueId()) == null) {
            Main.pfm_deathstreak.put(p.getUniqueId(), 0);
        }
        Main.pfm_deathstreak.put(p.getUniqueId(), 0);
    }

    public void addTime(Player p, int i) {
        if (Main.pfm_timeOnline.get(p.getUniqueId()) == null) {
            Main.pfm_timeOnline.put(p.getUniqueId(), 1);
        }
        int oldTime = Main.pfm_timeOnline.get(p.getUniqueId());
        int newTime = oldTime + i;
        Main.pfm_timeOnline.put(p.getUniqueId(), newTime);
    }

    public static Yaml getPlayerYaml(Player player) {
        return new Yaml(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "PlayerData" + File.separator + player.getUniqueId() + ".yml");
    }

    public static Yaml getOfflinePlayerYaml(String string) {
        return new Yaml(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "PlayerData" + File.separator + string + ".yml");
    }

    public void create(Player p) {
        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        if (f.exists()) {
            Yaml yaml = getPlayerYaml(p);
            yaml.save();
        } else {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Yaml yaml = getPlayerYaml(p);
        yaml.set("Information.Name", p.getName());
        yaml.set("Statistics.Joins", 0);
        yaml.set("Statistics.Kills", 0);
        yaml.set("Statistics.Deaths", 0);
        yaml.set("Statistics.Gems", 10);
        yaml.set("Statistics.Time Online", 0);
        yaml.set("Completed.Parkour", "no");
        yaml.set("Completed.Maze", "no");
        yaml.set("Statistics.Current_Killstreak", 0);
        yaml.set("Statistics.Longest_Killstreak", 0);
        yaml.set("Statistics.Current_Deathstreak", 0);
        yaml.set("DailyRewards.Claimed", "no");
        p.setLevel(p.getLevel() + 100);
        if (Main.pfm_joins.get(p.getUniqueId()) != null) {
            save(p);
            Main.log("[File] Saved loaded information for: " + p.getName());
            return;
        }
        yaml.save();
        String suuid = p.getUniqueId().toString().toLowerCase();
        Main.uuidConfig.getConfig().set(p.getName(), suuid);
        Main.uuidConfig.saveConfig();
        Main.log("[File] " + p.getName() + " added to uuids.yml");
        Main.log("[File] Created: " + p.getName());
    }

    public void save(Player p) {
        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        if (f.exists()) {
            Yaml yaml = getPlayerYaml(p);
            yaml.save();
        } else {
            try {
                f.createNewFile();
                create(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Yaml fc = getPlayerYaml(p);
        fc.set("Information.Name", p.getName());
        fc.set("Statistics.Joins", Main.pfm_joins.get(p.getUniqueId()));
        fc.set("Statistics.Kills", Main.pfm_kills.get(p.getUniqueId()));
        fc.set("Statistics.Deaths", Main.pfm_deaths.get(p.getUniqueId()));
        fc.set("Statistics.Gems", Main.pfm_totalGems.get(p.getUniqueId()));
        fc.set("Statistics.Time Online", Main.pfm_timeOnline.get(p.getUniqueId()));
        fc.set("Statistics.PVPLevel", Main.pfm_pvpLevel.get(p.getUniqueId()));
        fc.set("Statistics.PVPExp", Main.pfm_pvpExp.get(p.getUniqueId()));
        fc.set("Completed.Maze", Main.pfm_completedMaze.get(p.getUniqueId()));
        fc.set("Completed.Parkour", Main.pfm_completedParkour.get(p.getUniqueId()));
        fc.set("Toggled.Bar", Main.barEnabled.get(p.getUniqueId()));
        fc.set("Statistics.Current_Killstreak", Main.pfm_killstreak.get(p.getUniqueId()));
        fc.set("Statistics.Longest_Killstreak", Main.pfm_longestKillstreak.get(p.getUniqueId()));
        fc.set("Statistics.Current_Deathstreak", Main.pfm_deathstreak.get(p.getUniqueId()));
        fc.set("DailyRewards.Claimed", Main.dailyRewards.get(p.getUniqueId()));
        fc.save();
        Main.log("[File] Saved: " + p.getName());
    }

    public void load(Player p) {
        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        if (f.exists()) {
            Yaml yaml = getPlayerYaml(p);
            yaml.save();
            if (Main.firstJoin.get(p.getUniqueId()) == null) {
                Main.firstJoin.put(p.getUniqueId(), false);
            }
            if (!Main.firstJoin.get(p.getUniqueId())) {
                p.sendMessage(strings.getMessage("welcomeBack"));
            }
        } else {
            try {
                f.createNewFile();
                create(p);
                Bukkit.broadcastMessage(strings.getMessage("welcomeNew_1") + ChatColor.RED + p.getName() + strings.getMessage("welcomeNew_2"));
                p.sendMessage(ChatColor.GREEN + "Welcome to " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "PlasmaNetwork" + ChatColor.GREEN + ", " + ChatColor.RED + p.getName() + ChatColor.GREEN + "!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        check(p);
        Yaml fc = getPlayerYaml(p);
        Main.pfm_name.put(p.getUniqueId(), fc.getString("Information.Name"));
        Main.pfm_joins.put(p.getUniqueId(), fc.getInteger("Statistics.Joins"));
        Main.pfm_kills.put(p.getUniqueId(), fc.getInteger("Statistics.Kills"));
        Main.pfm_deaths.put(p.getUniqueId(), fc.getInteger("Statistics.Deaths"));
        Main.pfm_totalGems.put(p.getUniqueId(), fc.getInteger("Statistics.Gems"));
        Main.pfm_timeOnline.put(p.getUniqueId(), fc.getInteger("Statistics.Time Online"));
        Main.pfm_pvpLevel.put(p.getUniqueId(), fc.getInteger("Statistics.PVPLevel"));
        Main.pfm_pvpExp.put(p.getUniqueId(), fc.getInteger("Statistics.PVPExp"));
        Main.pfm_killstreak.put(p.getUniqueId(), fc.getInteger("Statistics.Current_Killstreak"));
        Main.pfm_longestKillstreak.put(p.getUniqueId(), fc.getInteger("Statistics.Longest_Killstreak"));
        Main.pfm_deathstreak.put(p.getUniqueId(), fc.getInteger("Statistics.Current_Deathstreak"));
        Main.pfm_completedMaze.put(p.getUniqueId(), fc.getString("Completed.Maze"));
        Main.pfm_completedParkour.put(p.getUniqueId(), fc.getString("Completed.Parkour"));
        Main.barEnabled.put(p.getUniqueId(), fc.getString("Toggled.Bar"));
        Main.dailyRewards.put(p.getUniqueId(), fc.getString("DailyRewards.Claimed"));
        Main.log("[File] Loaded: " + p.getName());
        if (Main.uuidConfig.getConfig().get(p.getName()) == null) {
            String suuid = p.getUniqueId().toString();
            Main.uuidConfig.getConfig().set(p.getName().toLowerCase(), suuid);
            Main.uuidConfig.saveConfig();
            Main.log("[File] " + p.getName() + " added to uuids.yml - possible name change.");
        }
    }

    public void check(Player p) {
        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "PlayerData" + File.separator + p.getUniqueId() + ".yml");
        if (f.exists()) {
            Yaml yaml = getPlayerYaml(p);
            yaml.save();
        } else {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Yaml fc = getPlayerYaml(p);
        if (fc.get("Information.Name") == null || (fc.getString("Information.Name").equalsIgnoreCase(p.getName()))) {
            fc.set("Information.Name", p.getName());
        }
        if (fc.get("Statistics.Kills") == null) {
            fc.set("Statistics.Kills", 0);
        }
        if (fc.get("Statistics.Deaths") == null) {
            fc.set("Statistics.Deaths", 0);
        }
        if (fc.get("Statistics.Joins") == null) {
            fc.set("Statistics.Joins", 1);
        }
        if (fc.get("Statistics.Gems") == null) {
            fc.set("Statistics.Gems", 10);
        }
        if (fc.get("Statistics.Time Online") == null) {
            fc.set("Statistics.Time Online", 0);
        }
        if (fc.get("Statistics.PVPLevel") == null) {
            fc.set("Statistics.PVPLevel", 0);
        }
        if (fc.get("Statistics.PVPExp") == null) {
            fc.set("Statistics.PVPExp", 50);
        }
        if (fc.get("Statistics.Current_Killstreak") == null) {
            fc.set("Statistics.Current_Killstreak", 0);
        }
        if (fc.get("Statistics.Longest_Killstreak") == null) {
            fc.set("Statistics.Longest_Killstreak", 0);
        }
        if (fc.get("Statistics.Current_Deathstreak") == null) {
            fc.set("Statistics.Current_Deathstreak", 0);
        }
        if (fc.get("Completed.Maze") == null) {
            fc.set("Completed.Maze", "no");
        }
        if (fc.get("Completed.Parkour") == null) {
            fc.set("Completed.Parkour", "no");
        }
        if (fc.get("Toggled.Bar") == null) {
            fc.set("Toggled.Bar", "yes");
        }
        if (fc.get("DailyRewards.Claimed") == null) {
            fc.set("DailyRewards.Claimed", "no");
        }
        fc.save();
    }

    public Yaml getPf(Player p) {
        return getPlayerYaml(p);
    }
}

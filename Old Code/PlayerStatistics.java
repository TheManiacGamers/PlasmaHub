//package me.yeroc.PlasmaHub.managers;
//
//import com.sk89q.minecraft.util.commands.ChatColor;
//import me.yeroc.PlasmaHub.Main;
//import me.yeroc.PlasmaHub.utils.TitleAPI.TitleAPI;
//import org.bukkit.Bukkit;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//import org.bukkit.potion.PotionEffectType;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by Corey on 22/01/2018.
// */
//public class PlayerStatistics {
//
//    static PlayerStatistics instance = new PlayerStatistics();
//    public HashMap<UUID, Integer> joins = new HashMap<>();
//    public HashMap<UUID, Integer> secondsOnline = new HashMap<>();
//    public HashMap<UUID, Integer> kills = new HashMap<>();
//    public HashMap<UUID, Integer> deaths = new HashMap<>();
//    public HashMap<UUID, Integer> killstreak = new HashMap<>();
//    public HashMap<UUID, Integer> longestKillstreak = new HashMap<>();
//    public HashMap<UUID, Integer> experience = new HashMap<>();
//    public HashMap<UUID, Integer> level = new HashMap<>();
//    public HashMap<UUID, Integer> bounty = new HashMap<>();
//    public HashMap<UUID, Integer> chatCredits = new HashMap<>();
//    public HashMap<UUID, Integer> bullseyeHits = new HashMap<>();
//    public HashMap<UUID, Integer> grenadesThrown = new HashMap<>();
//    public HashMap<UUID, Integer> flashBangsThrown = new HashMap<>();
//    public HashMap<UUID, Integer> smokeBombsThrown = new HashMap<>();
//    public HashMap<UUID, Integer> checkpointsFound = new HashMap<>();
//    public HashMap<UUID, Integer> achievementsCompleted = new HashMap<>();
//    public HashMap<UUID, Integer> currentDeathStreak = new HashMap<>();
//    public HashMap<UUID, Integer> easterEggsAmountFound = new HashMap<>();
//    public HashMap<UUID, List<String>> easterEggsFound = new HashMap<>();
//
//    public HashMap<UUID, String> group = new HashMap<>();
//    public HashMap<UUID, String> scoreboard = new HashMap<>();
//
//    private Strings strings = Strings.getInstance();
//    //    private Scoreboard sc = Scoreboard.getInstance();
//    private Main plugin;
//
//    private PlayerStatistics() {
//    }
//
//    public static PlayerStatistics getInstance() {
//        return instance;
//    }
//
//    public void load(Player p) {
//        File pfile = getPF(p);
////        File pfile = File(plugin.getDataFolder() + File.separator + "PlayerData" + File.separator + p.getUniqueId().toString() + ".yml");
//        FileConfiguration fc = YamlConfiguration.loadConfiguration(pfile);
//        if (pfile.exists()) {
//            currentDeathStreak.put(p.getUniqueId(), 0);
//            scoreboard.put(p.getUniqueId(), fc.getString("Scoreboard"));
//            group.put(p.getUniqueId(), fc.getString("Statistics.Group"));
//            joins.put(p.getUniqueId(), fc.getInt("Statistics.Total Joins") + 1);
//            kills.put(p.getUniqueId(), fc.getInt("Statistics.Total Kills"));
//            deaths.put(p.getUniqueId(), fc.getInt("Statistics.Total Deaths"));
//            killstreak.put(p.getUniqueId(), fc.getInt("Statistics.Current Killstreak"));
//            longestKillstreak.put(p.getUniqueId(), fc.getInt("Statistics.Longest Killstreak"));
//            experience.put(p.getUniqueId(), fc.getInt("Statistics.Experience"));
//            level.put(p.getUniqueId(), fc.getInt("Statistics.Level"));
//            secondsOnline.put(p.getUniqueId(), fc.getInt("Statistics.Seconds Online"));
//            bounty.put(p.getUniqueId(), fc.getInt("Statistics.Bounty"));
//            checkpointsFound.put(p.getUniqueId(), fc.getInt("Statistics.Checkpoints Found"));
//            achievementsCompleted.put(p.getUniqueId(), fc.getInt("Statistics.Achievements Completed"));
//            //////////////// EASTER EGGS //////////////////
//            easterEggsAmountFound.put(p.getUniqueId(), fc.getInt("Easter Eggs.Found Amount"));
//            easterEggsFound.put(p.getUniqueId(), fc.getStringList("Easter Eggs.Found"));
//        } else {
//            create(p);
//        }
//    }
//
//    public void create(Player p) {
//        File pfile = getPF(p);
//        FileConfiguration fc = YamlConfiguration.loadConfiguration(pfile);
//        fc.set("Playername", p.getName());
//        int zero = 0;
//        int one = 1;
//        fc.set("Scoreboard", "enabled");
//        fc.set("Statistics.Total Joins", one);
//        fc.set("Statistics.Group", "Thug");
//        fc.set("Statistics.Total Kills", zero);
//        fc.set("Statistics.Total Deaths", zero);
//        fc.set("Statistics.Current Killstreak", zero);
//        fc.set("Statistics.Longest Killstreak", zero);
//        fc.set("Statistics.Experience", 50);
//        fc.set("Statistics.Level", zero);
//        fc.set("Statistics.Seconds Online", zero);
//        fc.set("Statistics.Bounty", zero);
//        fc.set("Statistics.Checkpoints Found", zero);
//        fc.set("Statistics.Achievements Completed", zero);
//        // EASTER EGGS //
//        fc.set("Easter Eggs.Found Amount", 0);
//        fc.set("Easter Eggs.Found", null);
//        try {
//            fc.save(pfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        load(p);
//        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "minecraft:scoreboard teams join nocollide " + p.getName());
//        Main.log("Added " + p.getName() + " to nocollide scoreboard.");
//    }
//
//    public void save(Player p) {
//        File pfile = getPF(p);
//        FileConfiguration fc = YamlConfiguration.loadConfiguration(pfile);
//        fc.set("Playername", p.getName());
//        fc.set("Scoreboard", scoreboard.get(p.getUniqueId()));
//        fc.set("Statistics.Total Joins", joins.get(p.getUniqueId()));
//        fc.set("Statistics.Total Kills", kills.get(p.getUniqueId()));
//        fc.set("Statistics.Total Deaths", deaths.get(p.getUniqueId()));
//        fc.set("Statistics.Current Killstreak", killstreak.get(p.getUniqueId()));
//        fc.set("Statistics.Longest Killstreak", longestKillstreak.get(p.getUniqueId()));
//        fc.set("Statistics.Experience", experience.get(p.getUniqueId()));
//        fc.set("Statistics.Level", level.get(p.getUniqueId()));
//        fc.set("Statistics.Seconds Online", secondsOnline.get(p.getUniqueId()));
//        fc.set("Statistics.Bounty", bounty.get(p.getUniqueId()));
//        fc.set("Statistics.Checkpoints Found", checkpointsFound.get(p.getUniqueId()));
//        fc.set("Statistics.Achievements Completed", achievementsCompleted.get(p.getUniqueId()));
//        // EASTER EGGS
//        fc.set("Easter Eggs.Found Amount", easterEggsAmountFound.get(p.getUniqueId()));
//
//        if (easterEggsFound.get(p.getUniqueId()) == null) {
//            try {
//                fc.save(pfile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return;
//        }
//        fc.set("Easter Eggs.Found", easterEggsFound.get(p.getUniqueId()));
//        try {
//            fc.save(pfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getGroup(Player p) {
//        return group.get(p.getUniqueId());
//    }
//
//    public Integer getJoins(Player p) {
//        return joins.get(p.getUniqueId());
//    }
//
//    public Integer getHoursOnline(Player p) {
//        int i = secondsOnline.get(p.getUniqueId());
//        secondsToHours(i);
//        return i;
//    }
//
//    public Integer getKills(Player p) {
//        return kills.get(p.getUniqueId());
//    }
//
//    public Integer getDeaths(Player p) {
//        return deaths.get(p.getUniqueId());
//    }
//
//    public Integer getCurrentKillstreak(Player p) {
//        return killstreak.get(p.getUniqueId());
//    }
//
//    public Integer getCurrentDeathstreak(Player p) {
//        return currentDeathStreak.get(p.getUniqueId());
//    }
//
//    public Integer getLongestKillstreak(Player p) {
//        return longestKillstreak.get(p.getUniqueId());
//    }
//
//    public Integer getExperience(Player p) {
//        return experience.get(p.getUniqueId());
//    }
//
//    public Integer getLevel(Player p) {
//        return level.get(p.getUniqueId());
//    }
//
//    public Integer getBounty(Player p) {
//        return bounty.get(p.getUniqueId());
//    }
//
//    public Integer getChatCredits(Player p) {
//        return chatCredits.get(p.getUniqueId());
//    }
//
//    public Integer getBullseyeHits(Player p) {
//        return bullseyeHits.get(p.getUniqueId());
//    }
//
//    public Integer getGrenadesThrown(Player p) {
//        return grenadesThrown.get(p.getUniqueId());
//    }
//
//    public Integer getSmokeBombsThrown(Player p) {
//        return smokeBombsThrown.get(p.getUniqueId());
//    }
//
//    public Integer getFlashBangsThrown(Player p) {
//        return flashBangsThrown.get(p.getUniqueId());
//    }
//
//    public Integer getCheckpointsFound(Player p) {
//        return checkpointsFound.get(p.getUniqueId());
//    }
//
//    public Integer getAchievementsCompleted(Player p) {
//        return achievementsCompleted.get(p.getUniqueId());
//    }
//
//    public void death(Player p, Player k) {
//        addDeaths(p, 1);
//        addDeathStreak(p, 1);
//        checkEnterRage(p);
//        TitleAPI.sendActionBar(p, strings.pvp_killedBy + k.getName());
//        p.performCommand("tospawn");
//        resetCurrentKillstreak(p);
////        sc.updateScoreboard(p);
//    }
//
//    private void addDeathStreak(Player p, int a) {
//        currentDeathStreak.put(p.getUniqueId(), currentDeathStreak.get(p.getUniqueId()) + 1);
//    }
//
//    public void kill(Player p, Player k) {
//        addKills(k, p, 1);
//        addMoney(k, 7);
//        addStrength(k, 7);
//        addCurrentKillstreak(k, 1);
//        checkLevelRankup(k);
//        checkEnterDomination(k);
//        minusExperience(p, 5);
//        k.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "You killed " + p.getName());
//        k.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "You gained $7 and 7 Strength");
//        if (getCurrentKillstreak(k) >= getLongestKillstreak(k) + 1) {
//            longestKillstreak.put(k.getUniqueId(), killstreak.get(k.getUniqueId()));
//            TitleAPI.sendActionBar(k, ChatColor.RED + "New Longest killstreak of " + getCurrentKillstreak(k) + "! Here's $5 and 5 Strength!");
////            k.sendMessage(strings.defaultMsgs + "You have gained $5 and 5 Strength from reaching a new longest killstreak of " + getCurrentKillstreak(k));
//            addMoney(k, 5);
//            addStrength(k, 5);
//        }
////        sc.updateScoreboard(k);
//    }
//
//    private void checkEnterDomination(Player p) {
//        if (killstreak.get(p.getUniqueId()) % 7 == 0) {
//            Bukkit.broadcastMessage(strings.defaultMsgs + p.getDisplayName() + ChatColor.DARK_RED + " is dominating!");
//            Bukkit.broadcastMessage(strings.defaultMsgs + p.getDisplayName() + ChatColor.DARK_RED + " is on a rampage!");
//            p.addPotionEffect(PotionEffectType.REGENERATION.createEffect(300, 3));
//            p.addPotionEffect(PotionEffectType.SPEED.createEffect(300, 3));
//            p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(300, 3));
//            p.addPotionEffect(PotionEffectType.GLOWING.createEffect(Integer.MAX_VALUE, 3));
//        }
//    }
//
//    private void checkEnterRage(Player p) {
//        if (currentDeathStreak.get(p.getUniqueId()) % 7 == 0) {
//            Bukkit.broadcastMessage(strings.defaultMsgs + p.getDisplayName() + ChatColor.AQUA + " is getting totally wrecked!");
//            Bukkit.broadcastMessage(strings.defaultMsgs + p.getDisplayName() + ChatColor.AQUA + " is raging!");
//            p.addPotionEffect(PotionEffectType.REGENERATION.createEffect(300, 3));
//            p.addPotionEffect(PotionEffectType.SPEED.createEffect(300, 3));
//            p.addPotionEffect(PotionEffectType.JUMP.createEffect(300, 3));
//            p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(300, 3));
//        }
//    }
//
//    private void sendMessageLevelRankup(Player p) {
//        p.sendMessage(strings.defaultMsgs + "LEVEL UP!");
//        p.sendMessage(strings.defaultMsgs + "You are now level " + getLevel(p));
//        p.sendMessage(strings.defaultMsgs + "You have gained $5, 5 Strength and 5 Chat Credits!");
//    }
//
//    public void checkLevelRankup(Player p) { // EVERY KILL REMOVES 5 EXPERIENCE TO NEXT LEVEL
//        if (getExperience(p) == -5) {
//            setExperience(p, 0);
//            return;
//        }
//        if (getLevel(p) == 0) {
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//            }
//            setExperience(p, 50);
////            sc.updateScoreboard(p);
//            return;
//        }
//        minusExperience(p, 5);
//        if (getLevel(p) >= 10 && (!(getLevel(p) <= 11))) { // LEVEL 0 - 10
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 50); // 10 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 10) && getLevel(p) >= 11 && (!(getLevel(p) >= 16))) { // LEVEL 11 - 15
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 100); // 20 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 15) && getLevel(p) >= 16 && (!(getLevel(p) >= 21))) { // LEVEL 16-20
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 150); // 30 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 20) && getLevel(p) >= 21 && (!(getLevel(p) >= 26))) { // LEVEL 21-25
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 250); // 50 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            if (getLevel(p) == 25) {
//                p.sendMessage(ChatColor.GREEN + "You are One Quarter (1/4th) of the way to level 100!");
//                p.sendMessage(ChatColor.GREEN + "You have gained an extra $250 and 250 Strength!");
//                addMoney(p, 250);
//                addStrength(p, 250);
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 25) && getLevel(p) >= 26 && (!(getLevel(p) >= 31))) { // LEVEL 26-30
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 300); // 60 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 30) && getLevel(p) >= 31 && (!(getLevel(p) >= 41))) { // LEVEL 31-40
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 375); // 75 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 40) && getLevel(p) >= 41 && (!(getLevel(p) >= 51))) { // LEVEL 41-50
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 500); // 100 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            if (getLevel(p) == 50) {
//                p.sendMessage(ChatColor.GREEN + "You are half way to level 100!");
//                p.sendMessage(ChatColor.GREEN + "You have gained an extra $500 and 500 Strength!");
//                addMoney(p, 500);
//                addStrength(p, 500);
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 50) && getLevel(p) >= 51 && (!(getLevel(p) >= 76))) { // LEVEL 51-75
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 625); // 125 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            if (getLevel(p) == 75) {
//                p.sendMessage(ChatColor.GREEN + "You are Three Quarter (3/4th) of the way to level 100!");
//                p.sendMessage(ChatColor.GREEN + "You have gained an extra $750 and 750 Strength!");
//                addMoney(p, 750);
//                addStrength(p, 750);
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 75) && getLevel(p) >= 76 && (!(getLevel(p) >= 91))) { // LEVEL 76-90
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 750); // 150 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 90) && getLevel(p) >= 91 && (!(getLevel(p) >= 96))) { // LEVEL 91-95
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 875); // 175 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 90) && getLevel(p) >= 91 && (!(getLevel(p) >= 96))) { // LEVEL 95-99
//            if (getExperience(p) == 0) {
//                addLevel(p, 1);
//                setExperience(p, 1000); // 200 kills each level
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                return;
//            }
//            return;
//        }
//        if (!(getLevel(p) >= 99) && getLevel(p) >= 100 && (!(getLevel(p) >= 101))) { // LEVEL 100
//            if (getExperience(p) == 0) {
//                setExperience(p, 1500); // 300 kills
//                sendMessageLevelRankup(p);
////                sc.updateScoreboard(p);
//                p.sendMessage(strings.defaultMsgs + ChatColor.GREEN + "You have reached the final level! Congratulations!");
//                p.sendMessage(ChatColor.GREEN + "You have gained an extra $1000 and 1000 Strength!");
//                Bukkit.broadcastMessage(strings.broadcast + ChatColor.GREEN + p.getName() + " has reached level 100!");
//                addMoney(p, 750);
//                addStrength(p, 750);
//                return;
//            }
//            return;
//        } else {
//            return;
//        }
//    }
//
//    public void resetCurrentKillstreak(Player p) {
//        killstreak.remove(p.getUniqueId());
//        killstreak.put(p.getUniqueId(), 0);
//    }
//
//    public void resetLongestKillstreak(Player p) {
//        longestKillstreak.remove(p.getUniqueId());
//    }
//
//    public void addMoney(Player p, double a) {
//        Main.econ.depositPlayer(p, a);
////        sc.updateScoreboard(p);
//    }
//
//    public void addStrength(Player p, int a) {
//        p.setLevel(p.getLevel() + a);
//    }
//
//    public void addJoins(Player p, int a) {
//        int oldJoins = joins.get(p.getUniqueId());
//        joins.remove(p.getUniqueId());
//        joins.put(p.getUniqueId(), oldJoins + a);
//    }
//
//    public void addKills(Player k, Player p, int a) {
//        kills.put(k.getUniqueId(), kills.get(k.getUniqueId()) + a);
//    }
//
//    public void addDeaths(Player p, int a) {
//        deaths.put(p.getUniqueId(), deaths.get(p.getUniqueId()) + a);
//    }
//
//    public void addCurrentKillstreak(Player p, int a) {
//        killstreak.put(p.getUniqueId(), killstreak.get(p.getUniqueId()) + a);
//    }
//
//    public void addLongestKillstreak(Player p, int a) {
//        longestKillstreak.put(p.getUniqueId(), killstreak.get(p.getUniqueId()));
//    }
//
//    public void minusExperience(Player p, int a) {
//        int oldExperience = experience.get(p.getUniqueId());
//        int newExperience = oldExperience - a;
//        experience.remove(p.getUniqueId());
//        experience.put(p.getUniqueId(), newExperience);
////        sc.updateScoreboard(p);
//    }
//
//    public void setExperience(Player p, int a) {
//        experience.put(p.getUniqueId(), a);
//    }
//
//    public void addLevel(Player p, int a) {
//        level.put(p.getUniqueId(), level.get(p.getUniqueId()) + a);
//    }
//
//    public void addBounty(Player p, int a) {
//        bounty.put(p.getUniqueId(), bounty.get(p.getUniqueId()) + a);
////        sc.updateScoreboard(p);
//    }
//
//    public void minusChatCredits(Player p, int a) {
//        int oldChatCredits = chatCredits.get(p.getUniqueId());
//        int newChatCredits = oldChatCredits - a;
//        chatCredits.remove(p.getUniqueId());
//        chatCredits.put(p.getUniqueId(), newChatCredits);
////        sc.updateScoreboard(p);
//    }
//
//    public void addChatCredits(Player p, int a) {
//        chatCredits.put(p.getUniqueId(), chatCredits.get(p.getUniqueId()) + a);
//    }
//
//    public void addEasterEggsFound(Player p, int a) {
//        easterEggsAmountFound.put(p.getUniqueId(), easterEggsAmountFound.get(p.getUniqueId()) + a);
////        sc.updateScoreboard(p);
//    }
//
//    public void setEasterEggsFound(Player p, int a) {
//        easterEggsAmountFound.put(p.getUniqueId(), a);
////        sc.updateScoreboard(p);
//    }
//
//    public Integer getEasterEggsFound(Player p) {
//        return easterEggsAmountFound.get(p.getUniqueId());
//    }
//
//    public void addAchievementsCompleted(Player p, int a) {
//        achievementsCompleted.put(p.getUniqueId(), achievementsCompleted.get(p.getUniqueId()) + a);
////        sc.updateScoreboard(p);
//    }
//
//    public Integer secondsToHours(int i) {
//        i = i * (60 * 60 * 1000);
//        return i;
//    }
//
//    public File getPF(Player p) {
//        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("AggregationHub").getDataFolder(), File.separator + "PlayerData" + File.separator);
//        File pfile = new File(userdata, File.separator + p.getUniqueId() + ".yml");
//        return pfile;
//    }
//}

package me.yeroc.PlasmaHub.managers;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.AutoBroadcast;
import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.PlayerListener;
import me.yeroc.PlasmaHub.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Corey on 20/12/2018.
 */
public class Tasks extends BukkitRunnable {
    private int time = 0;

    private PlayerFileManager pfm = PlayerFileManager.getInstance();
    private AutoBroadcast ab = AutoBroadcast.getInstance();
    private Strings strings = Strings.getInstance();
    private API api = API.getInstance();

    public void run() {
        time++;
        if (time % 1 == 0) { // EVERY SECOND
            if (Bukkit.getOnlinePlayers().size() != 0) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    pfm.addTime(p, 1); // ADD TIME TO THE PLAYER EVERY SECOND.
                    if (Main.maze_effectedMazes.get(Bukkit.getServer()) != null && (Main.maze_loaded != null)) {
                        if (Main.maze_effectedMazes.get(Bukkit.getServer()).contains(Main.maze_loaded)) {
                            if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                                if (Main.maze_playerEffectSeconds.get(p.getUniqueId()) == null) {
                                    Main.maze_playerEffectSeconds.put(p.getUniqueId(), 0);
                                }
                                int i = Main.maze_playerEffectSeconds.get(p.getUniqueId());
                                int newint = i + 1;
                                Main.maze_playerEffectSeconds.put(p.getUniqueId(), newint);
                                if (i == 25) {
                                    Main.maze_playerEffectSeconds.put(p.getUniqueId(), 0);
                                    PlayerListener.getInstance().randomPotion(p);
                                }
                            }
                        }
                        continue;
                    }
                    if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
//                    TitleAPI.sendTitle(p, 0, 25, 0, strings.getMessage("parkour_isInParkour);
                        continue;
                    }
                    if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
//                    TitleAPI.sendTitle(p, 0, 25, 0, strings.getMessage("maze_youAreInMaze);
                        continue;
                    }
                    if (Main.kotl_player.equalsIgnoreCase(null) || (Main.kotl_player.equalsIgnoreCase("blank"))) {
                        continue;
                    }
                    if (Main.kotl_player.equalsIgnoreCase(p.getName())) {
                        if (Bukkit.getOnlinePlayers().size() >= 2 && (Main.kotl_playersInRegion.size() >= 2) && (Main.kotl_playerInRegion.get(p.getUniqueId()).equalsIgnoreCase("yes"))) {
                            if (Main.kotl_secondsAsKotl.get(p.getUniqueId()) == null) {
                                Main.kotl_secondsAsKotl.put(p.getUniqueId(), 1);
                                continue;
                            }
                            int kotlTime = (Main.kotl_secondsAsKotl.get(p.getUniqueId()));
                            int kotlTimeNew = kotlTime + 1;
                            Main.kotl_secondsAsKotl.put(p.getUniqueId(), kotlTimeNew);
                        }
                    } else {
                        Main.kotl_secondsAsKotl.remove(p.getUniqueId());
                    }
                }
            }
        }
        int abTime = Main.autoBroadcastTime * 20 * 60;
        if (time % abTime == 0) {
            ab.broadcast();
        }
    }
}

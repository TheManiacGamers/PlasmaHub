package me.yeroc.PlasmaHub.managers;

import me.yeroc.PlasmaHub.Main;

/**
 * Created by Corey on 28/11/2018.
 */
public class PermissionsManager {

    private static PermissionsManager instance = new PermissionsManager();
    private Main plugin;

    private PermissionsManager() {

    }

    public static PermissionsManager getInstance() {
        return instance;
    }

    // JOIN/LEAVE
    public String plasma_join_notify = ("plasma.join.notify");
    public String plasma_quit_notify = ("plasma.quit.notify");

    // COMMANDS CLASS
    public String plasma_command = ("plasma.command");
    public String plasma_command_setspawn = ("plasma.command.setspawn");
    public String plasma_command_spawn = ("plasma.command.spawn");
    public String plasma_command_reload = ("plasma.command.reload");
    public String plasma_command_stats = ("plasma.command.stats");
    public String plasma_command_resetAllInventory = ("plasma.command.resetallinventory");
    public String plasma_gamemode_creative = ("plasma.gamemode.creative");
    public String plasma_gamemode_survival = ("plasma.gamemode.survival");
    public String plasma_gamemode_advemture = ("plasma.gamemode.adventure");
    public String plasma_gamemode_spectator = ("plasma.gamemode.spectator");
    public String plasma_config_reload = ("plasma.config.reload");
    public String plasma_maze_add = ("plasma.command.maze.add");
    public String plasma_maze_load = ("plasma.command.maze.load");

    public String plasma_server_creative = ("plasma.server.creative");
    public String plasma_server_survival = ("plasma.server.survival");
    public String plasma_server_skyblock = ("plasma.server.skyblock");

    // DOUBLE JUMP
    public String plasma_doublejump_use = ("plasma.doublejump.use");
    public String plasma_doublejump_bypass = ("plasma.doublejump.bypass");

    public String plasma_snowball_use = ("plasma.snowball.use");

    public String plasma_music_use = ("plasma.music.use");
    public String plasma_gadgetsmenu_use = ("plasma.gadgetsmenu.use");

    // LAUNCH PADS
    public String launchpads_use = ("plasma.launchpads.use");

    // SERVER SELECTOR
    public String plasma_serverselector_use = ("plasma.serverselector.use");

    // KOTL
    public String plasma_kotl_use = ("plasma.kotl.use");

    // MINIGAMES CLASSES

    // PVP CLASS
    public String plasma_pvp_use = ("plasma.pvp.use");

    // GADGETS
    public String plasma_gadgets_buy = ("plasma.gadgets.buy");

    // PARKOUR
    public String plasma_parkour_use = ("plasma.parkour.use");

    // MAZE
    public String plasma_maze_use = ("plasma.maze.use");

    // MINIGAME
    public String plasma_minigame_bypass = ("plasma.minigame.bypass");

    public String plasma_bowride_use = ("plasma.bowride.use");

}

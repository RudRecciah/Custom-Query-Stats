package dev.rudrecciah.customquerystats.customquerystats;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class Main extends JavaPlugin implements Listener, CommandExecutor {

    public static Main plugin;
    StringBuilder motdAddition = new StringBuilder();

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getLogger().log(Level.INFO, "Plugin enabled.");
        Long period = getConfig().getLong("interval");
        this.getServer().getScheduler().runTaskTimer(this, this::motdstat, 0, period);
    }

    public void motdstat() {
        Boolean silent = getConfig().getBoolean("silent");
        Boolean verbose = getConfig().getBoolean("verbose");
        motdAddition.append( "\u00A7r\n\u00A7r\n" + "none");
        if(verbose) {
            getServer().getLogger().log(Level.INFO, String.valueOf(motdAddition));
        }
        List finalStats = new ArrayList();
        List<Statistic> statList = new ArrayList();
        statList.add(Statistic.ANIMALS_BRED);
        statList.add(Statistic.AVIATE_ONE_CM);
        statList.add(Statistic.BEACON_INTERACTION);
        statList.add(Statistic.BELL_RING);
        statList.add(Statistic.BOAT_ONE_CM);
        statList.add(Statistic.BREWINGSTAND_INTERACTION);
        statList.add(Statistic.CAULDRON_FILLED);
        statList.add(Statistic.CAULDRON_USED);
        statList.add(Statistic.CHEST_OPENED);
        statList.add(Statistic.CLIMB_ONE_CM);
        statList.add(Statistic.CRAFTING_TABLE_INTERACTION);
        statList.add(Statistic.CROUCH_ONE_CM);
        statList.add(Statistic.DAMAGE_ABSORBED);
        statList.add(Statistic.DAMAGE_BLOCKED_BY_SHIELD);
        statList.add(Statistic.DAMAGE_DEALT);
        statList.add(Statistic.DAMAGE_DEALT_ABSORBED);
        statList.add(Statistic.DAMAGE_DEALT_RESISTED);
        statList.add(Statistic.DAMAGE_RESISTED);
        statList.add(Statistic.DAMAGE_TAKEN);
        statList.add(Statistic.DEATHS);
        statList.add(Statistic.DROP_COUNT);
        statList.add(Statistic.ENDERCHEST_OPENED);
        statList.add(Statistic.FALL_ONE_CM);
        statList.add(Statistic.FISH_CAUGHT);
        statList.add(Statistic.FLY_ONE_CM);
        statList.add(Statistic.FURNACE_INTERACTION);
        statList.add(Statistic.HORSE_ONE_CM);
        statList.add(Statistic.INTERACT_WITH_ANVIL);
        statList.add(Statistic.INTERACT_WITH_BLAST_FURNACE);
        statList.add(Statistic.INTERACT_WITH_CAMPFIRE);
        statList.add(Statistic.INTERACT_WITH_CARTOGRAPHY_TABLE);
        statList.add(Statistic.INTERACT_WITH_GRINDSTONE);
        statList.add(Statistic.INTERACT_WITH_LECTERN);
        statList.add(Statistic.INTERACT_WITH_LOOM);
        statList.add(Statistic.INTERACT_WITH_SMITHING_TABLE);
        statList.add(Statistic.INTERACT_WITH_SMOKER);
        statList.add(Statistic.INTERACT_WITH_STONECUTTER);
        statList.add(Statistic.ITEM_ENCHANTED);
        statList.add(Statistic.JUMP);
        statList.add(Statistic.LEAVE_GAME);
        statList.add(Statistic.MINECART_ONE_CM);
        statList.add(Statistic.MOB_KILLS);
        statList.add(Statistic.OPEN_BARREL);
        statList.add(Statistic.PIG_ONE_CM);
        statList.add(Statistic.PLAY_ONE_MINUTE);
        statList.add(Statistic.PLAYER_KILLS);
        statList.add(Statistic.RAID_TRIGGER);
        statList.add(Statistic.RAID_WIN);
        statList.add(Statistic.SHULKER_BOX_OPENED);
        statList.add(Statistic.SLEEP_IN_BED);
        statList.add(Statistic.SNEAK_TIME);
        statList.add(Statistic.SPRINT_ONE_CM);
        statList.add(Statistic.STRIDER_ONE_CM);
        statList.add(Statistic.SWIM_ONE_CM);
        statList.add(Statistic.TIME_SINCE_DEATH);
        statList.add(Statistic.TIME_SINCE_REST);
        statList.add(Statistic.TRADED_WITH_VILLAGER);
        statList.add(Statistic.WALK_ONE_CM);
        OfflinePlayer[] players = getServer().getOfflinePlayers();
        if(players.length > 0) {
            List pCount = new ArrayList();
            for (OfflinePlayer p : players) {
                List pStat = new ArrayList();
                for (Statistic stat : statList) {
                    String statName = String.valueOf(stat).replaceAll("Statistic.", "");
                    Boolean statCheck = getConfig().getConfigurationSection("stats").getBoolean(statName);
                    if(verbose) {
                        System.out.println("Statistic information:" + "    toggle: " + statCheck + "    key: " + stat.getKey() + "    name: " + statName);
                    }
                    if(statCheck) {
                        int statKey = statList.indexOf(stat);
                        int statValue = p.getStatistic(stat);
                        String statFinal = "%y%" + statKey + "%u%" + statValue + "%y%";
                        pStat.add(statFinal);
                    }
                }
                String pName = p.getName();
                String pStatString = "%r%" + pName + "%t%" + pStat + "%r%";
                finalStats.add(pStatString);
            }
            String workingPlayerStatPackage = "%e%" + finalStats.toString() + "%e%";
            String replaceb4 = " ";
            String playerStatPackage = workingPlayerStatPackage.replaceAll(replaceb4, "");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);
            String motdAdditionString = "\u00A7r\n\u00A7r\n" + "%q%" + time + "%w%" + playerStatPackage + "%q%";
            int motdLastChar = motdAddition.length() - 1;
            motdAddition.replace(0, motdLastChar, motdAdditionString);
            if(!silent) {
                getServer().getLogger().log(Level.INFO, "MOTD Updated");
            }
            if(verbose) {
                getServer().getLogger().log(Level.INFO, String.valueOf(motdAddition));
            }
        }
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        String serverMOTD = e.getMotd();
        String replaceb4 = " ";
        String workingMOTD = serverMOTD + motdAddition;
        String replaceb1 = "\\[";
        String replaceb2 = "]";
        String replaceb3 = ",";
        String workingMOTD1 = workingMOTD.replaceAll(replaceb1,"");
        String workingMOTD2 = workingMOTD1.replaceAll(replaceb2,"");
        String finalMOTD = workingMOTD2.replaceAll(replaceb3,"");
        Boolean verbose = getConfig().getBoolean("verbose");
        if(!(finalMOTD.getBytes().length > 32767)) {
            e.setMotd(finalMOTD);
            if(verbose) {
                getServer().getLogger().log(Level.INFO, finalMOTD);
            }
        }else{
            getServer().getLogger().log(Level.SEVERE, "[CustomQueryStats] Error: MOTD Can't be updated, it's too big.");
        }
    }

    @Override
    public void onDisable() {
        getServer().getLogger().log(Level.INFO, "Plugin disabled.");
    }
}

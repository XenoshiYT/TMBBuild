package me.xenodev.tmbt.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.*;
import me.xenodev.tmbt.mysql.SQLTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SBBuilder {

    private static Team team;

    public static void setScoreboard(Player p) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("main", "main", "§7» §5§lT§deam§5§lM§dega§5§lB§dyte §7«");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        IPermissionUser permuser = CloudNetDriver.getInstance().getPermissionManagement().getUser(p.getUniqueId());
        IPermissionGroup permgroup = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(permuser);

        obj.getScore("§7§o").setScore(12);
        obj.getScore("§fDein Name").setScore(11);
        obj.getScore("§c§l" + p.getName()).setScore(10);
        obj.getScore("§6").setScore(9);
        obj.getScore("§fRang").setScore(8);
        obj.getScore(updateTeam(board, "rang", "§6§l" + permgroup.getName(), "", ChatColor.BLUE)).setScore(7);
        obj.getScore("§4").setScore(6);
        obj.getScore("§fOnlinezeit").setScore(5);
        obj.getScore(updateTeam(board, "time", "§a§l" + SQLTime.changeTime(p.getUniqueId()), "", ChatColor.YELLOW)).setScore(4);
        obj.getScore("§3").setScore(3);
        obj.getScore("§fTMB Teamspeak").setScore(2);
        obj.getScore("§d§l§oclan-tmb.de").setScore(1);

        p.setScoreboard(board);
    }

    public static void updateScoreboard(Player p) {
        Scoreboard board = p.getScoreboard();
        Objective obj = board.getObjective("main");

        IPermissionUser permuser = CloudNetDriver.getInstance().getPermissionManagement().getUser(p.getUniqueId());
        IPermissionGroup permgroup = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(permuser);

        obj.getScore(updateTeam(board, "rang", "§6§l" + permgroup.getName(), "", ChatColor.BLUE)).setScore(7);
        obj.getScore(updateTeam(board, "time", "§a§l" + SQLTime.changeTime(p.getUniqueId()), "", ChatColor.YELLOW)).setScore(4);
    }

    public static Team getTeam(Scoreboard board, String Team, String prefix, String suffix) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setAllowFriendlyFire(false);
        team.setCanSeeFriendlyInvisibles(true);

        return team;

    }

    public static String updateTeam(Scoreboard board, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());

        return entry.toString();
    }
}

package me.xenodev.tmbt.main;

import me.xenodev.tmbt.commands.*;
import me.xenodev.tmbt.events.JoinEvent;
import me.xenodev.tmbt.events.LeaveEvent;
import me.xenodev.tmbt.mysql.MySQL;
import me.xenodev.tmbt.mysql.SQLTime;
import me.xenodev.tmbt.utils.SBBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public int sched;
    public int sched1;
    public static String prefix = "§8[§5§lTMB§8] ";
    public static String error = "§8[§4ERROR§8] ";

    public static MySQL mysql;

    @Override
    public void onEnable() {
        Main.instance = this;
        ConnectMySQL();

        events();
        cmds();
        startScoreboard();
        startOnlinetime();
    }

    @Override
    public void onDisable() {

    }

    private void events(){
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new SpawnCMD(), this);
    }

    private void cmds(){
        getServer().getPluginCommand("gm").setExecutor(new GamemodeCMD());
        getServer().getPluginCommand("gamemode").setExecutor(new GamemodeCMD());

        getServer().getPluginCommand("sign").setExecutor(new SignateCMD());
        getServer().getPluginCommand("signate").setExecutor(new SignateCMD());

        getServer().getPluginCommand("rename").setExecutor(new RenameCMD());

        getServer().getPluginCommand("changelog").setExecutor(new ChangelogCMD());

        getServer().getPluginCommand("invsee").setExecutor(new InvseeCMD());
        getServer().getPluginCommand("endersee").setExecutor(new EnderseeCMD());

        getServer().getPluginCommand("spawn").setExecutor(new SpawnCMD());

        getServer().getPluginCommand("oz").setExecutor(new OnlinezeitCMD());
        getServer().getPluginCommand("ot").setExecutor(new OnlinezeitCMD());
        getServer().getPluginCommand("onlinezeit").setExecutor(new OnlinezeitCMD());
        getServer().getPluginCommand("onlinetime").setExecutor(new OnlinezeitCMD());

        getServer().getPluginCommand("tp").setExecutor(new TeleportCMD());
        getServer().getPluginCommand("tphere").setExecutor(new TeleportCMD());
        getServer().getPluginCommand("tpcord").setExecutor(new TeleportCMD());

        getServer().getPluginCommand("world").setExecutor(new BuildingWorldCMD());
        getServer().getPluginCommand("worlds").setExecutor(new BuildingWorldCMD());
    }

    private void ConnectMySQL(){
        mysql = new MySQL("localhost", "tmbmysql", "tmbmysql", "[BM7A6s5AeZmo6*]");
        mysql.update("CREATE TABLE IF NOT EXISTS Time(UUID VARCHAR(100),HOURS BIGINT,MINUTES INT,SECONDS INT)");
    }

    public void startScoreboard() {
        if (!Bukkit.getScheduler().isCurrentlyRunning(this.sched)) {
            this.sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, (Runnable)new Runnable() {
                @Override
                public void run() {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        SBBuilder.updateScoreboard(all);
                    }
                }
            }, 0L, 20L);
        }
    }

    public void startOnlinetime() {
        if (!Bukkit.getScheduler().isCurrentlyRunning(this.sched1)) {
            this.sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, (Runnable)new Runnable() {
                @Override
                public void run() {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        SQLTime.addSeconds(all.getUniqueId(), 1);
                        if(SQLTime.getSeconds(all.getUniqueId()) == 60){
                            SQLTime.addMinutes(all.getUniqueId(), 1);
                            SQLTime.setSeconds(all.getUniqueId(), 0);
                        }

                        if(SQLTime.getMinutes(all.getUniqueId()) == 60){
                            SQLTime.addHours(all.getUniqueId(), 1);
                            SQLTime.setMinutes(all.getUniqueId(), 0);
                        }
                    }
                }
            }, 0L, 20L);
        }
    }
}

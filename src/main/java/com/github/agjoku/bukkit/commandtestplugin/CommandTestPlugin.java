package com.github.agjoku.bukkit.commandtestplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public final class CommandTestPlugin extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("timecount")).setExecutor(this);
    }

    @EventHandler
    //エンティティーを右クリックするとサイドバーに現在のレベルが表示されるメソッド
    public void onPlayerInteractEntityEvent(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
        Objective objective = board.registerNewObjective("stats", "dummy", "stats");
        objective.setDisplayName("ステータス");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score level = objective.getScore("§aLevel: §f" + player.getLevel());
        level.setScore(1);
        player.setScoreboard(board);
    }

    public void ScoreboardDisplay(String count,CommandSender sender) {
        Player player = (Player)sender;
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective sidebar = scoreboard.registerNewObjective("Time","dummy", "Time");
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        sidebar.setDisplayName("Time");
        Score time = sidebar.getScore("§aTime: §f" + count);
        time.setScore(1);
        player.setScoreboard(scoreboard);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        int num = Integer.parseInt(args[0]);

        if (command.getName().equalsIgnoreCase("timecount")) {
            if(num < 0) {
                sender.sendMessage("§e1以上を指定してください");
                return true;
            } else if(num > 0) {
                this.ScoreboardDisplay(args[0],sender);
                return true;
            } else if(num == 0){
                sender.sendMessage("1以上を指定してください");
                return true;
            }
        }
        return false;
    }
}
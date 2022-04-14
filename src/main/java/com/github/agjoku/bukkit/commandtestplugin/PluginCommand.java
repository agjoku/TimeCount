package com.github.agjoku.bukkit.commandtestplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginCommand extends JavaPlugin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args) {
        if(command.getName().equalsIgnoreCase("timecount")) {

        }

        return  false;
    }
}

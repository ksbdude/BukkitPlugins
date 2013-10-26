package me.ksbdude;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft"); //logger
	public static Plugin plugin; //now Plugin = plugin in all code
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + "Has Been Disabled!");
		
	}
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " pdfFile.getVersion() + "Has Been Enabled!");
	}
	
	public	boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("setslapperspawn")){
			player.sendMessage(ChatColor.RED + "Slapper Spawn Set");
		}
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("sendme")){
			player.sendMessage(ChatColor.BLUE + "Sent");
		}
		return false;
	}
}//end braket

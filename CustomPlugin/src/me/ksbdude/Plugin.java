package me.ksbdude;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin{
	
    public File ArenaFile;
    protected File Arena;
    public File ArenaMangerFile;
    protected File ArenaManger;
    public File ArenaPVPFile;
    protected File ArenaPVP;
    
	public final Logger logger = Logger.getLogger("Minecraft"); //logger
	public static Plugin plugin; //now Plugin = plugin in all code
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + "Has Been Disabled!");
		System.out.println("[FriendsList] Disabled version: " + getDescription().getVersion());
	}
	
	@Override
	public void onEnable(){
		//PluginDescriptionFile pdfFile = this.getDescription();
		Arena = new File(getDataFolder(), "Arena.yml");
		Arena = new File(getDataFolder(), "ArenaPVP.yml");
		Arena = new File(getDataFolder(), "ArenaManger.yml");
	}
	
	public	boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("setslapperspawn")){
			player.sendMessage(ChatColor.RED + "Slapper Spawn Set");
		}
		if(commandLabel.equalsIgnoreCase("sendme")){
			player.sendMessage(ChatColor.BLUE + "Sent");
		}
		return false;
	}
}//end braket

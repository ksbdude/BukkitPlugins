package me.ksbdude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArenaManager{
        
        //save where the player teleported
        public Map<String, Location> locs = new HashMap<String, Location>();
        //make a new instance of the class
        public static ArenaManager a = new ArenaManager();
        //a few other fields
        Map<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();
        Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();
        //list of arenas
        List<Arena> arenas = new ArrayList<Arena>();
        int arenaSize = 0;
         
        ArenaPVP plugin;
        public ArenaManager(ArenaPVP arenaPVP) {
                this.plugin = arenaPVP;
        }
        
        public ArenaManager(){
                
        }

        //we want to get an instance of the manager to work with it statically
        public static ArenaManager getManager(){
            return a;
        }
         
        //get an Arena object from the list
        public Arena getArena(int i){
            for(Arena a : arenas){
                if(a.getId() == i){
                    return a;
                }
            }
            return null;
        }
         
        //add players to the arena, save their inventory   
        public void addPlayer(Player p, int i){
            Arena a = getArena(i);//get the arena you want to join
            if(a == null){//make sure it is not null
                p.sendMessage("Invalid arena!");
                return;
            }
        
            a.getPlayers().add(p.getName());//add them to the arena list of players
            inv.put(p.getName(), p.getInventory().getContents());//save inventory
            armor.put(p.getName(), p.getInventory().getArmorContents());
            
            p.getInventory().setArmorContents(null);
            p.getInventory().clear();
            
            p.teleport(a.spawn);//teleport to the arena spawn
            GameListener.add(p);
        }
           
        //remove players
        public void removePlayer(Player p){
            Arena a = null;//make an arena
            for(Arena arena : arenas){
                if(arena.getPlayers().contains(p.getName())){
                    a = arena;//if the arena has the player, the arena field would be the arena containing the player
                }
                //if none is found, the arena will be null
            }
            if(a == null || !a.getPlayers().contains(p.getName())){//make sure it is not null
                p.sendMessage("Invalid operation!");
                return;
            }
               
           a.getPlayers().remove(p.getName());//remove from arena
           
           p.getInventory().clear();
           p.getInventory().setArmorContents(null);
           
           p.getInventory().setContents(inv.get(p.getName()));//restore inventory
           p.getInventory().setArmorContents(armor.get(p.getName()));
               
           inv.remove(p.getName());//remove entries from hashmaps
           armor.remove(p.getName());
           p.teleport(locs.get(p.getName()));
           locs.remove(p.getName());        
        }
         
        //create arena
        public void createArena(Location l){
           int num = arenaSize + 1;
           arenaSize++;
         
           Arena a = new Arena(l, num);
           arenas.add(a);
           
           plugin.getConfig().set("Arenas" + num, serializeLoc(l));
           List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
           list.add(num);
           plugin.getConfig().set("Arenas.Arenas", list);
           plugin.saveConfig();
        }
        
        public void removeArena(int i){
                for(String s : getArena(i).getPlayers()){
                        Player p = Bukkit.getPlayerExact(s);
                        if(p != null){
                                p.teleport(locs.get(p.getName()));
                                locs.remove(p.getName());
                        }
                }
                arenas.remove(getArena(i));
                List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
                list.remove(i);
                plugin.getConfig().set("Arenas.Arenas", list);
                plugin.saveConfig();
        }
        
        public boolean isInGame(Player p){
                for(Arena a : arenas){
                        if(a.getPlayers().contains(p.getName()))
                                return true;
                }
                return false;
        }
        
        public void loadGames(){
                if(plugin.getConfig().getIntegerList("Arenas.Arenas").isEmpty()){
                        return;
                }
                
                for(int i : plugin.getConfig().getIntegerList("Arenas.Arenas")){
                        Arena a = new Arena(deserializeLoc(plugin.getConfig().getString("Arenas" + i)), i);
                        arenas.add(a);
                }
        }
        public String serializeLoc(Location l){
                return l.getWorld().getName()+","+l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ();
        }
        public Location deserializeLoc(String s){
                String[] st = s.split(",");
                return new Location(Bukkit.getWorld(st[1]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
        }
}        
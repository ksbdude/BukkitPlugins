package me.ksbdude;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class Arena{

        //you want some info about the arena stored here
            int number = 0;//the arena id
            Location spawn = null;//spawn location for the arena
            List<String> players = new ArrayList<String>();//list of players

        //now let's make a few getters/setters, and a constructor
            public Arena(Location loc, int id){
                this.spawn = loc;
                this.number = id;
            }

            public int getId(){
                return this.number;
            }

            public List<String> getPlayers(){
                return this.players;
            }
        }
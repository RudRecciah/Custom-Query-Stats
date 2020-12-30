//unused class, made for integration with a dependency that was scrapped later, can be used for dependency in forks so leaving here for anyone who might be interested in trying it out
package dev.rudrecciah.customquerystats.customquerystats.customconfig;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private static File file;
    private static FileConfiguration customFile;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("ServerlistMOTD").getDataFolder(), "config.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                //nothing to see here
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try{
            customFile.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save file");
        }
    }
}
/*
* Methods:
* CustomConfig.setup();
* CustomConfig.get().options().copyDefaults(true);
* CustomConfig.save();
* */

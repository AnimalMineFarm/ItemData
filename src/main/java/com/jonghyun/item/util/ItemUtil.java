package com.jonghyun.item.util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ItemUtil {

    public static HashMap<Integer, ItemStack> id = new HashMap<>();

    public static void register()
    {
        id.clear();
        File file = new File("plugins/ItemData/ItemList.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        if(file.exists())
        {
            for(String a : yaml.getKeys(false))
            {
                int code = Integer.parseInt(a);
                ItemStack stack = yaml.getItemStack(a);
                id.put(code, stack);
            }
        }
    }

    public static void unregister()
    {
        File file = new File("plugins/ItemData/ItemList.yml");
        file.delete();
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        try {
             for(Map.Entry<Integer,ItemStack> entry : id.entrySet())
             {
                 yaml.set(entry.getKey() + "", entry.getValue());
             }
             yaml.save(file);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

package com.jonghyun.item.manager;

import com.jonghyun.item.util.ItemUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ItemManager {

    public static void addItem(ItemStack stack, int id)
    {
        ItemUtil.id.put(id, stack);
    }

    public static void removeItem(int id)
    {
        ItemUtil.id.remove(id);
    }

    public static ItemStack getItem(int id)
    {
        return ItemUtil.id.get(id);
    }

    public static void printAllItem(Player p)
    {
        for(Map.Entry<Integer, ItemStack> entry : ItemUtil.id.entrySet())
        {
            p.sendMessage("§7[ §a아이템 데이터 §7] " + entry.getKey() + ". " + entry.getValue().getItemMeta().getDisplayName());
        }
    }

    public static boolean isExistsItem(int id)
    {
        return ItemUtil.id.get(id) != null ? true : false;
    }


}

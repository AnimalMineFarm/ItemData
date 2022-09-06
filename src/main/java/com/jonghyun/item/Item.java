package com.jonghyun.item;

import com.jonghyun.item.command.ItemCommand;
import com.jonghyun.item.command.ItemTabComplete;
import com.jonghyun.item.util.ItemUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class Item extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemUtil.register();

        getCommand("아이템데이터").setExecutor(new ItemCommand());
        getCommand("아이템데이터").setTabCompleter(new ItemTabComplete());
    }

    @Override
    public void onDisable() {
        ItemUtil.unregister();
    }
}

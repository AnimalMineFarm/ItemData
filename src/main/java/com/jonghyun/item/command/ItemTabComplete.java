package com.jonghyun.item.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemTabComplete implements TabCompleter {

    private static final String[] ORIGINAL = {"이름", "로어", "로어삭제", "태그", "내구도", "저장", "삭제", "지급", "목록", "reload"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args[0].equalsIgnoreCase("태그"))
        {
            List<String> completions1 = new ArrayList<>();
            for(ItemFlag flag : ItemFlag.values())
            {
                completions1.add(flag.name());
            }

            List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], completions1, completions);
            Collections.sort(completions);

            return completions;
        }
        List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], Arrays.asList(ORIGINAL), completions);
        Collections.sort(completions);
        return completions;
    }
}

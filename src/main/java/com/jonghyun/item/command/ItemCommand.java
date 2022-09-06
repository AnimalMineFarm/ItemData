package com.jonghyun.item.command;

import com.jonghyun.item.manager.ItemManager;
import com.jonghyun.item.util.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;
            if(p.isOp())
            {
                if(args.length == 0)
                {
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 이름 [이름]");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 로어 [라인] [텍스트]");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 로어삭제 [라인]");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 태그 [tag]");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 내구도 [수치]");
                    p.sendMessage("");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 저장 [id]");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 삭제 [id]");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 지급 [id]");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 목록");
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 reload");
                    return false;
                }

                if(args[0].equalsIgnoreCase("저장"))
                {
                    if(args.length == 2)
                    {
                        int id = Integer.parseInt(args[1]);
                        if(p.getItemInHand() != null)
                        {
                            ItemManager.addItem(p.getItemInHand(), id);
                            p.sendMessage("§7[ §a아이템 데이터 §7] §a추가 완료");
                            return false;
                        }
                        p.sendMessage("§7[ §a아이템 데이터 §7] §c아이템을 들고 입력해주세요");
                        return false;
                    }
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 저장 [id]");
                    return false;
                }

                if(args[0].equalsIgnoreCase("삭제"))
                {
                    if(args.length == 2)
                    {
                        int id = Integer.parseInt(args[1]);
                        if(ItemManager.isExistsItem(id))
                        {
                            ItemManager.removeItem(id);
                            p.sendMessage("§7[ §a아이템 데이터 §7] §a삭제 완료");
                            return false;
                        }
                        p.sendMessage("§7[ §a아이템 데이터 §7] §c존재하지 않는 id입니다");
                        return false;
                    }
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 삭제 [id]");
                    return false;
                }

                if(args[0].equalsIgnoreCase("지급"))
                {
                    if(args.length == 2)
                    {
                        int id = Integer.parseInt(args[1]);
                        if(ItemManager.isExistsItem(id))
                        {
                            p.getInventory().addItem(ItemManager.getItem(id));
                            p.sendMessage("§7[ §a아이템 데이터 §7] §a지급 완료");
                            return false;
                        }
                        p.sendMessage("§7[ §a아이템 데이터 §7] §c존재하지 않는 id입니다");
                        return false;
                    }
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 삭제 [id]");
                    return false;
                }

                if(args[0].equalsIgnoreCase("목록"))
                {
                    ItemManager.printAllItem(p);
                    return false;
                }

                if(args[0].equalsIgnoreCase("reload"))
                {
                    ItemUtil.unregister();
                    ItemUtil.register();
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f리로드 완료");
                    return false;
                }

                if(args[0].equalsIgnoreCase("이름"))
                {
                    if(args.length >= 2)
                    {
                        if(p.getItemInHand() != null)
                        {
                            StringBuilder name = new StringBuilder();
                            for(int i = 1; i < args.length - 1; i++)
                                name.append(ChatColor.translateAlternateColorCodes('&', args[i]) + " ");
                            name.append(ChatColor.translateAlternateColorCodes('&', args[args.length - 1]));
                            ItemMeta meta = p.getItemInHand().getItemMeta();
                            meta.setDisplayName(name.toString());
                            p.getItemInHand().setItemMeta(meta);
                            return false;
                        }
                        p.sendMessage("§7[ §a아이템 데이터 §7] §c아이템을 들고 입력해주세요");
                        return false;
                    }
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 이름 [이름]");
                    return false;
                }

                if(args[0].equalsIgnoreCase("로어"))
                {
                    if(args.length >= 3)
                    {
                        if(p.getItemInHand() != null)
                        {
                            ItemMeta meta = p.getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            if(meta.hasLore())
                                lore = meta.getLore();
                            int count = Integer.parseInt(args[1]);
                            if(lore.size() < count)
                            {
                                for(int i = 0; i < count - lore.size(); i++)
                                    lore.add("");
                            }

                            StringBuilder name = new StringBuilder();
                            for(int i = 2; i < args.length - 1; i++)
                                name.append(ChatColor.translateAlternateColorCodes('&', args[i]) + " ");
                            name.append(ChatColor.translateAlternateColorCodes('&', args[args.length - 1]));
                            lore.set(count - 1, name.toString());
                            meta.setLore(lore);
                            p.getItemInHand().setItemMeta(meta);
                            return false;
                        }
                        p.sendMessage("§7[ §a아이템 데이터 §7] §c아이템을 들고 입력해주세요");
                        return false;
                    }
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 로어 [라인] [텍스트]");
                    return false;
                }

                if(args[0].equalsIgnoreCase("로어삭제"))
                {
                    if(args.length == 2)
                    {
                        if(p.getItemInHand() != null)
                        {
                            ItemMeta meta = p.getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            if(meta.hasLore())
                                lore = meta.getLore();
                            int count = Integer.parseInt(args[1]);
                            lore.remove(count - 1);
                            meta.setLore(lore);
                            p.getItemInHand().setItemMeta(meta);
                            return false;
                        }
                        p.sendMessage("§7[ §a아이템 데이터 §7] §c아이템을 들고 입력해주세요");
                        return false;
                    }
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 로어삭제 [라인]");
                    return false;
                }

                if(args[0].equalsIgnoreCase("내구도"))
                {
                    if(args.length == 2)
                    {
                        if(p.getItemInHand() != null)
                        {
                            int count = Integer.parseInt(args[1]);
                            p.getItemInHand().setDurability((short) count);
                            return false;
                        }
                        p.sendMessage("§7[ §a아이템 데이터 §7] §c아이템을 들고 입력해주세요");
                        return false;
                    }
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 로어삭제 [라인]");
                    return false;
                }

                if(args[0].equalsIgnoreCase("태그"))
                {
                    if(args.length == 2)
                    {
                        if(p.getItemInHand() != null)
                        {
                            if(ItemFlag.valueOf(args[1]) == null)
                            {
                                p.sendMessage("§7[ §a아이템 데이터 §7] §c존재하지 않는 태그입니다. 태그 목록 :");
                                p.sendMessage("§7[ §a아이템 데이터 §7] §fHIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS, HIDE_PLACED_ON, HIDE_UNBREAKABLE, HIDE_POTION_EFFECTS");
                                return false;
                            }
                            ItemFlag flag = ItemFlag.valueOf(args[1]);
                            ItemMeta meta = p.getItemInHand().getItemMeta();
                            if(meta.getItemFlags().contains(flag))
                            {
                                if(flag == ItemFlag.HIDE_UNBREAKABLE)
                                    meta.setUnbreakable(false);
                                meta.removeItemFlags(flag);
                            } else {
                                if(flag == ItemFlag.HIDE_UNBREAKABLE)
                                    meta.setUnbreakable(true);
                                meta.addItemFlags(flag);
                            }
                            p.getItemInHand().setItemMeta(meta);
                            return false;
                        }
                        p.sendMessage("§7[ §a아이템 데이터 §7] §c아이템을 들고 입력해주세요");
                        return false;
                    }
                    p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 태그 [태그]");
                    return false;
                }

                p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 이름 [이름]");
                p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 로어 [라인] [텍스트]");
                p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 태그 [tag]");
                p.sendMessage("");
                p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 저장 [id]");
                p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 삭제 [id]");
                p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 지급 [id]");
                p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 목록");
                p.sendMessage("§7[ §a아이템 데이터 §7] §f/아이템데이터 reload");
                return false;
            }
            return false;
        }
        return false;
    }
}

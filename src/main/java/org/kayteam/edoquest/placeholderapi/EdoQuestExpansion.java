package org.kayteam.edoquest.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.kayteam.edoquest.EdoQuest;
import org.kayteam.edoquest.prestige.Prestige;

import java.util.ArrayList;

public class EdoQuestExpansion extends PlaceholderExpansion {

    private final EdoQuest plugin;

    public EdoQuestExpansion(EdoQuest plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return plugin.getDescription().getName();
    }

    @Override
    public @NotNull String getAuthor() {
        return "KayTeam";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if("next_prestige".equals(params)){
            try{
                ArrayList<Prestige> prestiges = new ArrayList<>(plugin.getPrestigeManager().getPrestigiesMap().values());
                int nextPrestigeIndex = prestiges.indexOf(plugin.getPrestigeManager().getPlayerPrestige(player.getPlayer()))+1;
                if(nextPrestigeIndex <= prestiges.size()){
                    return ChatColor.translateAlternateColorCodes('&', ((Prestige)prestiges.get(nextPrestigeIndex)).getDisplayName());
                }else{
                    return ChatColor.translateAlternateColorCodes('&', plugin.getSettings().getString("placeholders.maxPrestigeReached"));
                }
            }catch (Exception e){
                return "Error.";
            }
        }else if("display_name".equals(params)){
            try{
                return ChatColor.translateAlternateColorCodes('&', plugin.getPrestigeManager().getPlayerPrestige(player.getPlayer()).getDisplayName());
            }catch (Exception e){
                return "Error.";
            }
        }else{
            return "Invalid.";
        }
    }
}

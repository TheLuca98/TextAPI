/*
This file is part of TextAPI.
Copyright (C) 2015 Luca P. <https://github.com/TheLuca98>

TextAPI is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

TextAPI is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with TextAPI. If not, see <http://www.gnu.org/licenses/>.
*/
package io.github.theluca98.textapi;

import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Represents a title that appears at the center of the screen.
 * @author Luca
 */
public class Title {
    
    private String title, subtitle;
    private int fadeIn, fadeOut, stay;

    /**
     * Constructs a {@link Title} object.
     * @param title The text of the main title.
     * @param subtitle The text of the subtitle.
     * @param fadeIn The fade-in time of the title (in ticks).
     * @param stay The stay time of the title (in ticks).
     * @param fadeOut The fade-out time of the title (in ticks).
     */
    public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.stay = stay;
    }
    
    /**
     * Sends the title to a specific player.
     * @param player The player to send the title to.
     */
    public void send(Player player) {
        PlayerConnection pc = ((CraftPlayer) player).getHandle().playerConnection;
        PacketPlayOutTitle timesPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
        pc.sendPacket(timesPacket);
        if (title != null) {
            IChatBaseComponent titleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleComponent);
            pc.sendPacket(titlePacket);
        }
        if (subtitle != null) {
            IChatBaseComponent subtitleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleComponent);
            pc.sendPacket(subtitlePacket);
        }
    }
    
    /**
     * Sends the title to all online players.
     */
    public void sendToAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player);
        }
    }

    /**
     * Getter for the text of the main title.
     * @return Text of main title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the text of the subtitle.
     * @return Text of subtitle.
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Getter for the fade-in time (in ticks).
     * @return Fade-in ticks.
     */
    public int getFadeIn() {
        return fadeIn;
    }

    /**
     * Getter for the fade-out time (in ticks).
     * @return Fade-out ticks.
     */
    public int getFadeOut() {
        return fadeOut;
    }

    /**
     * Getter for the stay time (in ticks).
     * @return Stay ticks.
     */
    public int getStay() {
        return stay;
    }

    /**
     * Setter for the text of the main title.
     * @param title New main title text.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for the text of the subtitle.
     * @param subtitle New subtitle text.
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Setter for the fade-in time (in ticks).
     * @param fadeIn New fade-in ticks.
     */
    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    /**
     * Setter for the fade-out time (in ticks).
     * @param fadeOut New fade-out ticks.
     */
    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    /**
     * Setter for the stay time (in ticks).
     * @param stay New stay ticks.
     */
    public void setStay(int stay) {
        this.stay = stay;
    }

}
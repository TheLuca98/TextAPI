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
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Utility class for sending action bar messages to players.
 * @author Luca
 */
public class ActionBar {
    
    /**
     * Sends an action bar message to a specific player.
     * @param player The player to send the message to.
     * @param message The message to send.
     */
    public static void send(Player player, String message) {
        PlayerConnection pc = ((CraftPlayer) player).getHandle().playerConnection;
        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(component, (byte) 2);
        pc.sendPacket(packet);
    }
    
    /**
     * Sends an action bar message to all online players.
     * @param message The message to send.
     */
    public static void sendToAll(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player, message);
        }
    }

}

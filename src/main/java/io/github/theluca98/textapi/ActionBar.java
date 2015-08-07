/*
 This file is part of TextAPI 2.0.
 Copyright (c) 2015 Luca P. <https://github.com/TheLuca98>

 TextAPI is free software: you can redistribute it and/or modify it under the
 terms of the GNU Lesser General Public License as published by the Free
 Software Foundation, either version 3 of the License, or (at your option) any
 later version.

 TextAPI is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.

 You should have received a copy of the GNU Lesser General Public License along
 with TextAPI. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.theluca98.textapi;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a message displayed above the hotbar.
 *
 * @author Luca
 */
public class ActionBar {

    /**
     * Used to toggle debug messages. Disabled by default.a
     */
    public static boolean DEBUG;

    private JSONObject json;

    /**
     * Constructs an {@link ActionBar} object based on plain text.
     *
     * @param text Text to display.
     */
    public ActionBar(String text) {
        Preconditions.checkNotNull(text);
        this.json = Title.convert(text);
    }

    /**
     * Constructs an {@link ActionBar} object based on JSON-formatted text.
     *
     * @param json Text to display Must be in /tellraw JSON format.
     */
    public ActionBar(JSONObject json) {
        Preconditions.checkNotNull(json);
        Preconditions.checkArgument(!json.isEmpty());
        this.json = json;
    }

    /**
     * Sends an action bar message to a specific player.
     *
     * @param player The player to send the message to.
     */
    public void send(Player player) {
        Preconditions.checkNotNull(player);
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player),
                    connection = handle.getClass().getField("playerConnection").get(handle),
                    component = ServerPackage.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, json.toString()),
                    packet = ServerPackage.MINECRAFT.getClass("PacketPlayOutChat").getConstructor(ServerPackage.MINECRAFT.getClass("IChatBaseComponent"), byte.class).newInstance(component, (byte) 2);
            connection.getClass().getMethod("sendPacket", ServerPackage.MINECRAFT.getClass("Packet")).invoke(connection, packet);
        } catch (Exception e) {
            if (DEBUG) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed to send action bar message.", e);
            } else {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed to send action bar message to {0}. Is TextAPI updated?", player.getName());
            }
        }
    }

    /**
     * Sends an action bar message to all online players.
     */
    public void sendToAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player);
        }
    }

    /**
     * Changes the text to display.
     *
     * @param text Text to display.
     */
    public void setText(String text) {
        Preconditions.checkNotNull(text);
        this.json = Title.convert(text);
    }

    /**
     * Changes the text to display.
     *
     * @param json Text to display. Must be in /tellraw JSON format.
     */
    public void setJsonText(JSONObject json) {
        Preconditions.checkNotNull(json);
        Preconditions.checkArgument(!json.isEmpty());
        this.json = json;
    }

    /**
     * This method has been kept just to ensure backwards compatibility with older versions of TextAPI.
     * It is not supported and will be removed in a future release.
     *
     * @param player  The player to send the message to.
     * @param message The message to send.
     * @deprecated Please create a new {@link ActionBar} instance instead.
     */
    @Deprecated
    public static void send(Player player, String message) {
        new ActionBar(message).send(player);
    }

    /**
     * This method has been kept just to ensure backwards compatibility with older versions of TextAPI.
     * It is not supported and will be removed in a future release.
     *
     * @param message The message to send.
     * @deprecated Please create a new {@link ActionBar} instance instead.
     */
    @Deprecated
    public static void sendToAll(String message) {
        new ActionBar(message).sendToAll();
    }

}

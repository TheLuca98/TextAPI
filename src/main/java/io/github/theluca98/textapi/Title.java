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

/**
 * Represents a title that appears at the center of the screen.
 *
 * @author Luca
 */
public class Title {

    /**
     * Used to toggle debug messages. Disabled by default.
     *
     * @deprecated No longer in use.
     */
    @Deprecated
    public static boolean DEBUG;
    private JSONObject title, subtitle;
    private int fadeIn, fadeOut, stay;

    /**
     * Constructs a {@link Title} object.
     *
     * @param title    The text of the main title.
     * @param subtitle The text of the subtitle.
     * @param fadeIn   The fade-in time of the title (in ticks).
     * @param stay     The stay time of the title (in ticks).
     * @param fadeOut  The fade-out time of the title (in ticks).
     */
    public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = convert(title);
        this.subtitle = convert(subtitle);
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.stay = stay;
    }

    /**
     * Constructs a {@link Title} object.
     *
     * @param title    The text of the main title. Must be in /tellraw JSON format.
     * @param subtitle The text of the subtitle. Must be in /tellraw JSON
     *                 format.
     * @param fadeIn   The fade-in time of the title, in ticks.
     * @param stay     The stay time of the title, in ticks.
     * @param fadeOut  The fade-out time of the title, in ticks.
     */
    public Title(JSONObject title, JSONObject subtitle, int fadeIn, int fadeOut, int stay) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.stay = stay;
    }

    static JSONObject convert(String text) {
        JSONObject json = new JSONObject();
        json.put("text", text);
        return json;
    }

    /**
     * Sends the title to a specific player.
     *
     * @param player The player to send the title to.
     */
    public void send(Player player) {
        Preconditions.checkNotNull(player);
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            // NMS Classes
            Class<?> clsPacketPlayOutTitle = ServerPackage.MINECRAFT.getClass("PacketPlayOutTitle");
            Class<?> clsPacket = ServerPackage.MINECRAFT.getClass("Packet");
            Class<?> clsIChatBaseComponent = ServerPackage.MINECRAFT.getClass("IChatBaseComponent");
            Class<?> clsChatSerializer = ServerPackage.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer");
            Class<?> clsEnumTitleAction = ServerPackage.MINECRAFT.getClass("PacketPlayOutTitle$EnumTitleAction");
            Object timesPacket = clsPacketPlayOutTitle.getConstructor(int.class, int.class, int.class).newInstance(fadeIn, stay, fadeOut);
            playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, timesPacket);
            // Play the title packet
            if (title != null && !title.isEmpty()) {
                Object titleComponent = clsChatSerializer.getMethod("a", String.class).invoke(null, title.toString());
                Object titlePacket = clsPacketPlayOutTitle.getConstructor(clsEnumTitleAction, clsIChatBaseComponent).newInstance(clsEnumTitleAction.getField("TITLE").get(null), titleComponent);
                playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, titlePacket);
            }
            // Play the subtitle packet
            if (subtitle != null && !subtitle.isEmpty()) {
                Object subtitleComponent = clsChatSerializer.getMethod("a", String.class).invoke(null, subtitle.toString());
                Object subtitlePacket = clsPacketPlayOutTitle.getConstructor(clsEnumTitleAction, clsIChatBaseComponent).newInstance(clsEnumTitleAction.getField("SUBTITLE").get(null), subtitleComponent);
                playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, subtitlePacket);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
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
     *
     * @return Text of main title.
     */
    public JSONObject getTitle() {
        return title;
    }

    /**
     * Setter for the text of the main title.
     *
     * @param title New main title text.
     */
    public void setTitle(String title) {
        this.title = convert(title);
    }

    /**
     * Setter for the text of the main title.
     *
     * @param title New main title text. Must be in /tellraw JSON format.
     */
    public void setTitle(JSONObject title) {
        this.title = title;
    }

    /**
     * Getter for the text of the subtitle.
     *
     * @return Text of subtitle.
     */
    public JSONObject getSubtitle() {
        return subtitle;
    }

    /**
     * Setter for the text of the subtitle.
     *
     * @param subtitle New subtitle text.
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = convert(subtitle);
    }

    /**
     * Setter for the text of the subtitle.
     *
     * @param subtitle New subtitle text. Must be in /tellraw JSON format.
     */
    public void setSubtitle(JSONObject subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Getter for the fade-in time, in ticks.
     *
     * @return Fade-in ticks.
     */
    public int getFadeIn() {
        return fadeIn;
    }

    /**
     * Setter for the fade-in time, in ticks.
     *
     * @param fadeIn New fade-in ticks.
     */
    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    /**
     * Getter for the fade-out time, in ticks.
     *
     * @return Fade-out ticks.
     */
    public int getFadeOut() {
        return fadeOut;
    }

    /**
     * Setter for the fade-out time, in ticks.
     *
     * @param fadeOut New fade-out ticks.
     */
    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    /**
     * Getter for the stay time, in ticks.
     *
     * @return Stay ticks.
     */
    public int getStay() {
        return stay;
    }

    /**
     * Setter for the stay time, in ticks.
     *
     * @param stay New stay ticks.
     */
    public void setStay(int stay) {
        this.stay = stay;
    }

}

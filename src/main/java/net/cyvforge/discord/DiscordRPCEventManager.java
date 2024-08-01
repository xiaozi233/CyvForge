package net.cyvforge.discord;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class DiscordRPCEventManager {

    @SubscribeEvent
    public void logInEvent(EntityJoinWorldEvent e) {
        if (e.entity == null || !(e.entity.equals(Minecraft.getMinecraft().thePlayer))) return;
        try {
            if (Minecraft.getMinecraft().isSingleplayer()) {
                DiscordRPCHandler.updateStatus("Playing Singleplayer", null, null);
                return;
            }

            ServerData data = Minecraft.getMinecraft().getCurrentServerData();
            if (data != null && data.serverIP.equalsIgnoreCase("play.hpknetwork.xyz")) {
                DiscordRPCHandler.updateStatus("Playing Multiplayer", "hpk", "play.hpknetwork.xyz");
            } else {
                DiscordRPCHandler.updateStatus("Playing Multiplayer", null, null);
            }


        } catch (Exception ignored) {}
    }

    @SubscribeEvent
    public void logoutEvent(GuiOpenEvent e) {
        String text = null;
        GuiScreen gui = e.gui;

        if (gui instanceof GuiMultiplayer || gui instanceof GuiScreenServerList
                || gui instanceof GuiScreenAddServer) text = "In Multiplayer Menu";
        else if (gui instanceof GuiMainMenu) text = "In Main Menu";
        else if (gui instanceof GuiSelectWorld) text = "In Singleplayer Menu";
        else if (gui instanceof GuiConnecting || gui instanceof GuiDisconnected) text = "Connecting...";

        if (Minecraft.getMinecraft().theWorld == null && text != null) {
            DiscordRPCHandler.updateStatus(text, null, null);
        }
    }

}

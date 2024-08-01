package net.cyvforge.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.cyvforge.CyvForge;
import org.apache.logging.log4j.LogManager;

public class DiscordRPCHandler {
    public static DiscordRichPresence presence;
    static long start_time;

    public static void init() {
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "1122509451920936971";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> LogManager.getLogger().info("CyvForge discordRPC ready!");
        lib.Discord_Initialize(applicationId, handlers, true, null);
        start_time = System.currentTimeMillis() / 1000;
        updateStatus("Just started", null, null);

        // in a worker thread
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }

    public static void updateStatus(String firstline, String smallImage, String ip) {
        try {
            presence = new DiscordRichPresence();
            presence.largeImageKey = "icon";
            presence.largeImageText = "CyvForge " + CyvForge.VERSION;
            presence.startTimestamp = start_time;
            presence.smallImageKey = smallImage;
            presence.smallImageText = ip;
            presence.details = firstline;

            DiscordRPC.INSTANCE.Discord_UpdatePresence(presence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

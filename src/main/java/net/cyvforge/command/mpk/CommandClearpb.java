package net.cyvforge.command.mpk;

import net.cyvforge.CyvForge;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

public class CommandClearpb extends CyvCommand {
    public CommandClearpb() {
        super("clearpb");
        this.helpString = "Clear the landing block's existing offsets.";
    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        if (ParkourTickListener.landingBlock != null) {
            ParkourTickListener.landingBlock.pb = null;
            ParkourTickListener.landingBlock.pbX = null;
            ParkourTickListener.landingBlock.pbZ = null;
        }
        CyvForge.sendChatMessage("Successfully cleared landing offsets.");
    }
}
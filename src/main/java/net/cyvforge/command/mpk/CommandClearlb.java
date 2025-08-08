package net.cyvforge.command.mpk;

import net.cyvforge.CyvForge;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

public class CommandClearlb extends CyvCommand {
    public CommandClearlb() {
        super("clearlb");
        this.helpString = "Clear the landing block.";
    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        if (ParkourTickListener.landingBlock != null) ParkourTickListener.landingBlock = null;
        CyvForge.sendChatMessage("Successfully cleared landing block.");
    }
}
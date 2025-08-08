package net.cyvforge.command.mpk;

import net.cyvforge.CyvForge;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

public class CommandClearmm extends CyvCommand {
    public CommandClearmm() {
        super("clearmm");
        this.helpString = "Clear the momentum block.";
    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        if (ParkourTickListener.momentumBlock != null) ParkourTickListener.momentumBlock = null;
        CyvForge.sendChatMessage("Successfully cleared momentum block.");
    }
}
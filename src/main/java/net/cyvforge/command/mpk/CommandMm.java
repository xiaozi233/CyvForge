package net.cyvforge.command.mpk;

import net.cyvforge.CyvForge;
import net.cyvforge.event.events.GuiHandler;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.gui.GuiLb;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

public class CommandMm extends CyvCommand {
    public CommandMm() {
        super("mm");
        aliases.add("momentumblock");
        aliases.add("momentum");
        this.helpString = "Open GUi for modifying momentum block settings.";
    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        if (ParkourTickListener.momentumBlock != null) GuiHandler.setScreen(new GuiLb(ParkourTickListener.momentumBlock));
        else {
            CyvForge.sendChatMessage("You must set a momentum block to use this command.");
        }
    }
}
package net.cyvforge.command;

import net.cyvforge.event.events.GuiHandler;
import net.cyvforge.gui.GuiMPK;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

public class CommandGui extends CyvCommand {
    public CommandGui() {
        super("gui");
        this.helpString = "Open the MPK gui.";
    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        GuiHandler.setScreen(new GuiMPK());
    }
}
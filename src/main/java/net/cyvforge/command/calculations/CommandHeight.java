package net.cyvforge.command.calculations;

import net.cyvforge.CyvForge;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

import java.text.DecimalFormat;

public class CommandHeight extends CyvCommand {
    public CommandHeight() {
        super("height");
        this.hasArgs = true;
        this.usage = "<airtime> [ceiling_height]";
        this.helpString = "Calculate elevation change given airtime and optional ceiling.";

    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        try {
            double vy = 0.42;
            double y = 0;
            double ticks = 0;
            double final_tick = Integer.parseInt(args[0]);
            double ceiling;

            if (args.length < 2) ceiling = 4;
            else ceiling = Double.parseDouble(args[1]);

            if (final_tick > 1000) {
                CyvForge.sendChatMessage("Please input an airtime duration below 1000.");
                return;
            }

            if (final_tick < 1) {
                CyvForge.sendChatMessage("Please input an airtime duration above 1.");
                return;
            }

            if (ceiling < 1.8) {
                CyvForge.sendChatMessage("Ceiling must be above 1.8 blocks in height.");
                return;
            }

            while (ticks < final_tick) {
                y = y + vy;

                if (y > ceiling - 1.8) {
                    y = ceiling - 1.8;
                    vy = 0;
                }

                vy = (vy - 0.08) * 0.98;
                if (Math.abs(vy) < 0.00549) {
                    vy = 0;
                }
                ticks++;
            }

            DecimalFormat df = CyvForge.df;
            CyvForge.sendChatMessage("Change in height: " + df.format(y));

        } catch (Exception e) {
            CyvForge.sendChatMessage("Please input a valid jump.");

        }
    }
}

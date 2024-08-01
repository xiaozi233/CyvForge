package net.cyvforge.command.calculations;

import net.cyvforge.CyvForge;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

import java.text.DecimalFormat;

public class CommandDistance extends CyvCommand {
    public CommandDistance() {
        super("distance");
        this.hasArgs = true;
        this.usage = "<x> <y>";
        this.helpString = "Calculate raw distance and angle of a diagonal jump given x and z.";

        this.aliases.add("dist");
        this.aliases.add("rawdistance");
        this.aliases.add("rawdist");

    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        try {
            double x = Double.parseDouble(args[0]);
            double z = Double.parseDouble(args[1]);

            if ((x < 0) || (z < 0)) {
                CyvForge.sendChatMessage("Please input valid jump dimensions.");
                return;
            }

            if (x < 0.6) {
                x = 0.6;
            }

            if (z < 0.6) {
                z = 0.6;
            }

            DecimalFormat df = CyvForge.df;

            double rawDistance = Math.sqrt(Math.pow((x - 0.6),2) + Math.pow((z - 0.6),2));
            double angle = Math.atan((z-0.6)/(x-0.6))*180/Math.PI;

            CyvForge.sendChatMessage("Distance for jump dimensions " + x + " x " + z + ":"
                    + "\nJump length: " + df.format(rawDistance + 0.6) + "b"
                    + "\nRaw distance: " + df.format(rawDistance) + "m"
                    + "\nAngle: " + df.format(angle) + "\u00B0");

        } catch(Exception e) {
            CyvForge.sendChatMessage("Please input valid jump dimensions.");

        }
    }
}

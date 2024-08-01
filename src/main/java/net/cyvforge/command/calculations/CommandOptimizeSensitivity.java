package net.cyvforge.command.calculations;

import net.cyvforge.CyvForge;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;

import java.text.DecimalFormat;

public class CommandOptimizeSensitivity extends CyvCommand {
    public CommandOptimizeSensitivity() {
        super("optimizesensitivity");
        this.usage = "<angle>";
        this.helpString = "Optimize current sensitivity to be able to turn given degree in a whole number of pixels.";

        this.aliases.add("os");
        this.aliases.add("optimizesens");
        this.aliases.add("optimisesens");
        this.aliases.add("optimisesensitivity");
    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        try {
            //calculations
            double angle = Math.abs(Float.parseFloat(args[0]));
            float currentSens = Minecraft.getMinecraft().gameSettings.mouseSensitivity;

            double currentPx = (angle / 1.2) / Math.pow((currentSens * 0.6) + 0.2, 3);
            double newSens = (Math.cbrt((angle / (1.2 * Math.round(currentPx)))) - 0.2) / 0.6;

            if (newSens > 1) {
                newSens = (Math.cbrt((angle / (1.2 * Math.ceil(currentPx)))) - 0.2) / 0.6;
            } else if (newSens < 0) {
                newSens = (Math.cbrt((angle / (1.2 * Math.floor(currentPx)))) - 0.2) / 0.6;
            }


            //check

            if (currentSens > 1 || currentSens < 0) {

                CyvForge.sendChatMessage("Sensitivity optimising unavailable for sensitivities above 200% or below 0%.");
                return;

            }

            //response

            if (Math.round(currentPx) == 0) {

                CyvForge.sendChatMessage("Angle is too low to optimize for.");

            } else {

                Minecraft.getMinecraft().gameSettings.mouseSensitivity = (float) newSens;
                Minecraft.getMinecraft().gameSettings.saveOptions();

                double percentage = 200 * newSens;

                DecimalFormat df = CyvForge.df;

                CyvForge.sendChatMessage("Sensitivity optimized to " + df.format(percentage) + "%"
                        + "\nPixels of turning: " + Math.round(currentPx));

            }

        } catch (Exception e) {

            CyvForge.sendChatMessage("Please input a valid turning angle to optimize for.");

        }
    }
}

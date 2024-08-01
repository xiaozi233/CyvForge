package net.cyvforge.command;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;

public class CommandPositionChecker extends CyvCommand {
    public CommandPositionChecker() {
        super("positionchecker");
        this.hasArgs = true;
        this.usage = "[subcategory]";
        this.helpString = "Configure position checker. Use /cyv help pos for more info.";
        this.aliases = new ArrayList<String>();
        aliases.add("pos");
        aliases.add("poscheck");
        aliases.add("poschecker");
    }


    @Override
    public void run(ICommandSender sender, String[] args) {
        boolean enabled = CyvClientConfig.getBoolean("positionCheckerEnabled", false);
        boolean zneo = CyvClientConfig.getBoolean("positionCheckerZNeo", false);
        String subcategory = "toggle";

        //add a checker for no args to not toggle on/off inertia

        if (args.length != 0) {
            subcategory = args[0].toLowerCase();
        }

        if (subcategory.equals("toggle")) {
            if (enabled) {
                CyvClientConfig.set("positionCheckerEnabled", false);
                CyvForge.sendChatMessage("Position checker toggled off.");
            } else {
                CyvClientConfig.set("positionCheckerEnabled", true);
                CyvForge.sendChatMessage("Position checker toggled on.");
            }

        } else if (subcategory.equals("minx") || subcategory.equals("xmin")) {
            try {
                CyvClientConfig.set("positionCheckerMinX", Double.parseDouble(args[1]));
                CyvForge.sendChatMessage("Position checker minimum x coordinate set to " + args[1] + ".");
            } catch (Exception e) {
                CyvForge.sendChatMessage("Invalid minimum x coordinate.");
            }

        } else if (subcategory.equals("maxx") || subcategory.equals("xmax")) {
            try {
                CyvClientConfig.set("positionCheckerMaxX", Double.parseDouble(args[1]));
                CyvForge.sendChatMessage("Position checker maximum x coordinate set to " + args[1] + ".");
            } catch (Exception e) {
                CyvForge.sendChatMessage("Invalid maximum x coordinate.");
            }

        } else if (subcategory.equals("minz") || subcategory.equals("zmin")) {
            try {
                CyvClientConfig.set("positionCheckerMinZ", Double.parseDouble(args[1]));
                CyvForge.sendChatMessage("Position checker minimum z coordinate set to " + args[1] + ".");
            } catch (Exception e) {
                CyvForge.sendChatMessage("Invalid minimum z coordinate.");
            }

        } else if (subcategory.equals("maxz") || subcategory.equals("zmax")) {
            try {
                CyvClientConfig.set("positionCheckerMaxZ", Double.parseDouble(args[1]));
                CyvForge.sendChatMessage("Position checker maximum z coordinate set to " + args[1] + ".");
            } catch (Exception e) {
                CyvForge.sendChatMessage("Invalid maximum z coordinate.");
            }

        } else if (subcategory.equals("zneo") || subcategory.equals("z")) {
            if (zneo) {
                CyvClientConfig.set("positionCheckerZNeo", false);
                CyvForge.sendChatMessage("Z neo mode toggled off.");
            } else {
                CyvClientConfig.set("positionCheckerZNeo", true);
                CyvForge.sendChatMessage("Z neo mode toggled on.");
            }

        } else if (subcategory.equals("tick") || subcategory.equals("t")) {
            try {
                if (Integer.parseInt(args[1]) > 12 || Integer.parseInt(args[1]) < 1) {
                    CyvForge.sendChatMessage("Position checker tick must be from 1 to 12.");
                } else {
                    CyvClientConfig.set("positionCheckerTick", Integer.parseInt(args[1]));
                    CyvForge.sendChatMessage("Position checker tick set to " + args[1] + ".");
                }
            } catch (Exception e) {
                CyvForge.sendChatMessage("Invalid tick.");
            }

        } else {
            CyvForge.sendChatMessage("Unrecognized argument.");
        }
    }

    @Override
    public String getDetailedHelp() {
        return "Toggle on/off or modify position checker settings. Subcategories:"
                + "\n[toggle]: Toggle listener on or off."
                + "\n[minX/maxX/minZ/maxZ]: Set minimum and maximum coordinate bounds for a chat message."
                + "\n[zneo]: Toggle z neo mode on or off."
                + "\n[tick]: Set airtick to check your position.";
    }

}

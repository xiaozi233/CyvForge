package net.cyvforge.util.defaults;

import net.cyvforge.CyvForge;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.cyvforge.event.CommandInitializer.cyvCommands;

public class DefaultCommand extends CommandBase {

    @Override
    public String getCommandName() {
        // TODO Auto-generated method stub
        return "cyv";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        // TODO Auto-generated method stub
        return "/cyv <subcommand>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.<String>asList(new String[] {"mpk", "mm"});
    }

    @SuppressWarnings("deprecation")
    @Override
    public void processCommand(ICommandSender sender, String[] argsRaw) throws CommandException {
        if (argsRaw.length > 0) { //command exists
            String[] args = new String[argsRaw.length - 1];
            System.arraycopy(argsRaw, 1, args, 0, argsRaw.length - 1);

            //all arguments following /mpk
            CyvCommand targetCommand = null;

            Outer: for (CyvCommand cmd : cyvCommands) { //loop through subcommands
                if (!cmd.name.equalsIgnoreCase(argsRaw[0])) {
                    for (String s : cmd.aliases) {
                        if (s.equalsIgnoreCase(argsRaw[0])) {
                            targetCommand = cmd;
                            break Outer;
                        }
                    }
                } else {
                    targetCommand = cmd;
                    break;
                }
            }

            if (targetCommand != null) {
                targetCommand.run(sender, args);
                return;
            }

            //finished looping through with no matches?
            CyvForge.sendChatMessage("Unknown command. For more info use /cyv help");


        } else { //no command listed
            CyvForge.sendChatMessage("Use /cyv help for a list of commands.");
            return;

        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> allCommandNames = new ArrayList<String>();

        for (CyvCommand c : cyvCommands) {
            allCommandNames.add(c.name);
            if (c.aliases != null) allCommandNames.addAll(c.aliases);
        }

        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, allCommandNames);

        } else {
            return null;
        }


    }


}
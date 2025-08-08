package net.cyvforge.command.checkpoint;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.util.ChatFormattingString;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;

import java.text.DecimalFormat;
import java.util.Arrays;

public class CommandGiveCheckpointGenerator extends CyvCommand {
    public CommandGiveCheckpointGenerator() {
        super("getcheckpointgenerator");
        this.hasArgs = true;
        this.usage = "[x] [y] [z] [yaw] [pitch] [optional custom name]";
        this.helpString = "Give the player a custom command block capable of creating a checkpoint item. " +
                "If no coordinate provided, defaults to your current position.";

        this.aliases.add("getcpgenerator");
        this.aliases.add("getcpgen");
    }

    @Override
    public void run(ICommandSender sender, String[] args) {
        if (!MinecraftServer.getServer().isSinglePlayer()) {
            CyvForge.sendChatMessage("Checkpoint generators cannot be created in multiplayer.");
            return;
        }

        try {
            EntityPlayerMP player = CommandBase.getPlayer(Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().thePlayer.getName());

            String itemName = "dye";
            String itemData = "" + CyvClientConfig.getInt("generatorDyeColor", 1);
            String slot = "" + CyvClientConfig.getInt("generatorItemSlot", 0);

            String coordX, coordY, coordZ, coordYaw, coordPitch;

            if (args.length == 0) {
                DecimalFormat df = CyvForge.df;

                coordX = df.format(player.posX);
                coordY = df.format(player.posY);
                coordZ = df.format(player.posZ);
                coordYaw = df.format(player.rotationYaw);
                coordPitch = df.format(player.rotationPitch);

            } else {
                coordX = args[0];
                coordY = args[1];
                coordZ = args[2];
                coordYaw = args[3];
                coordPitch = args[4];
            }


            String name;

            if (args.length > 5) {
                String[] customName = (String[]) Arrays.copyOfRange(args, 5, args.length);
                String joinedCustomName = String.join(" ", customName);

                name = ChatFormattingString.RED+joinedCustomName;


            } else {
                name = ChatFormattingString.RED+"Checkpoint Item";

            }


            String command = "replaceitem entity @p slot.hotbar." + slot + " " + itemName + " 1 " + itemData
                    + " {display:{Name:\"" + name + ChatFormattingString.RESET + "\""
                    + ",Lore:[0:\"" + ChatFormattingString.YELLOW + "Checkpoint Teleport Item" + ChatFormattingString.RESET + "\","
                    + "1:\"" + ChatFormattingString.YELLOW + "Coords: " + ChatFormattingString.AQUA +
                    coordX + " / " + coordY + " / " + coordZ + " (" + coordYaw + " / " + coordPitch + ")" + ChatFormattingString.RESET
                    + "\"]}"
                    + ",isCP:true,coordX:" + coordX
                    + ",coordY:" + coordY
                    + ",coordZ:" + coordZ
                    + ",coordYaw:" + coordYaw
                    + ",coordPitch:" + coordPitch

                    + "}";

            Item item = CommandBase.getItemByText(Minecraft.getMinecraft().thePlayer, "command_block");
            ItemStack itemStack = new ItemStack(item, 1, 0);

            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            nbtTagCompound.setTag("BlockEntityTag", new NBTTagCompound());
            nbtTagCompound.getCompoundTag("BlockEntityTag").setString("Command", command);

            nbtTagCompound.setTag("display", new NBTTagCompound());
            nbtTagCompound.getCompoundTag("display").setString("Name", ChatFormattingString.RED + "Checkpoint Generator");

            NBTTagList lore = new NBTTagList();
            lore.appendTag(new NBTTagString(ChatFormattingString.YELLOW+"Checkpoint Generator Block"));
            lore.appendTag(new NBTTagString(ChatFormattingString.YELLOW+"Coords: "
                    +ChatFormattingString.AQUA+coordX+" / "+coordY+" / "+coordZ+" ("+coordYaw+" / "+coordPitch+")"));
            lore.appendTag(new NBTTagString(ChatFormattingString.YELLOW+"Name: "+name));

            nbtTagCompound.getCompoundTag("display").setTag("Lore", lore);

            itemStack.setTagCompound(nbtTagCompound);

            EntityItem entityItem = player.dropPlayerItemWithRandomChoice(itemStack, false);
            entityItem.setNoPickupDelay();
            entityItem.setOwner(player.getName());
        } catch (Exception e) {
            CyvForge.sendChatMessage("Please enter valid coords.");
            return;
        }

        CyvForge.sendChatMessage("Checkpoint Generator Command Block successfully given.");
    }

}

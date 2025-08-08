package net.cyvforge.hud;

import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

public class LabelBundle {
    FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
    public List<DraggableHUDElement> labels = new ArrayList<DraggableHUDElement>();

    public int getLabelWidth(String s) {
        return getLabelWidth(s, false);
    }

    public int getLabelWidth(String s, boolean angle) {
        font = Minecraft.getMinecraft().fontRendererObj;

        StringBuilder str;
        if (angle) str = new StringBuilder(s + ": 000.");
        else str = new StringBuilder(s + ": 000000.");
        for (int i = 0; i< CyvClientConfig.getInt("df",5); i++) str.append("0");
        if (angle) str.append("\u00B0");
        return font.getStringWidth(str.toString());
    }

    public int getLabelHeight() {
        return 9;
    }

}

package net.cyvforge.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.ConfigLoader;
import net.cyvforge.hud.HUDManager;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.cyvforge.util.GuiUtils;
import net.cyvforge.util.defaults.CyvGui;
import net.cyvforge.util.HUDPreset;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;
import java.io.*;
import java.util.ArrayList;

public class GuiPresets extends CyvGui {
    int sizeX = 220;
    int sizeY = 180;
    public ArrayList<PresetLine> presetLines = new ArrayList<>();
    SubButton addNewButton;
    File presetDir;

    public GuiPresets() {
        super("HUD Presets");
        this.presetDir = new File(Minecraft.getMinecraft().mcDataDir, "config/cyvforge/presets");
        if (!presetDir.exists()) presetDir.mkdirs();
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.addNewButton = new SubButton("Create New Preset", this.width / 2 - 75, this.height / 2 + sizeY / 2 + 10, 150, 20);
        this.addNewButton.setEnabled(true);
        refreshPresets();
        saveCurrentLayoutToSelected();
    }

    public void refreshPresets() {
        presetLines.clear();
        File[] files = presetDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File f : files) {
                presetLines.add(new PresetLine(f));
            }
        }

        String selected = CyvClientConfig.getString("selectedPreset", "none");
        boolean found = false;
        PresetLine toApply = null;

        for (PresetLine line : presetLines) {
            if (line.file.getName().equals(selected)) {
                found = true;
                break;
            }
        }

        if (!found && !presetLines.isEmpty()) {
            toApply = presetLines.get(0);
            CyvClientConfig.set("selectedPreset", toApply.file.getName());
            toApply.applyPreset();
            ConfigLoader.save(CyvForge.config, false);
        } else if (presetLines.isEmpty()) {
            CyvClientConfig.set("selectedPreset", "none");
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        int startX = this.width / 2 - sizeX / 2;
        int startY = this.height / 2 - sizeY / 2;

        GuiUtils.drawRoundedRect(startX - 5, startY - 5, startX + sizeX + 5, startY + sizeY + 5, 5, CyvForge.theme.background1);
        GuiUtils.drawCenteredString("HUD Presets", this.width / 2, startY + 5, 0xFFFFFFFF, true);
        this.addNewButton.draw(mouseX, mouseY);

        String currentSelected = CyvClientConfig.getString("selectedPreset", "");
        for (int i = 0; i < presetLines.size(); i++) {
            int yPos = startY + 30 + (i * 22);
            PresetLine line = presetLines.get(i);

            if (line.file.getName().equals(currentSelected)) {
                GuiUtils.drawRectOutline(startX + 1, yPos - 3, startX + sizeX, yPos + 16, 0xFFFFFFFF);
            }
            line.draw(yPos, mouseX, mouseY);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseEvent) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseEvent);
        if (this.addNewButton.clicked(mouseX, mouseY, mouseEvent)) {
            saveCurrentAsNew();
            refreshPresets();
            return;
        }
        int startY = this.height / 2 - sizeY / 2;
        ArrayList<PresetLine> tempLines = new ArrayList<>(presetLines);
        for (int i = 0; i < tempLines.size(); i++) {
            int yPos = startY + 30 + (i * 22);
            if (tempLines.get(i).mouseClicked(yPos, mouseX, mouseY, mouseEvent)) break;
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        ArrayList<PresetLine> linesCopy = new ArrayList<>(presetLines);
        for (PresetLine line : linesCopy) {
            if (line.nameField.isFocused()) {
                line.nameField.textboxKeyTyped(typedChar, keyCode);
                if (keyCode == Keyboard.KEY_RETURN) {
                    line.saveName();
                    refreshPresets();
                    return;
                }
            }
        }
    }

    private void saveCurrentAsNew() {
        String name = "Preset " + (presetLines.size() + 1);
        HUDPreset preset = new HUDPreset(name);
        for (DraggableHUDElement e : HUDManager.registeredRenderers) {
            ScreenPosition p = (e.position != null) ? e.position : e.load();
            if (p == null) p = e.getDefaultPosition();
            preset.positions.put(e.getName(), p.getAbsoluteX() + "," + p.getAbsoluteY() + "," + e.isVisible + "," + e.isEnabled);
        }
        savePresetToFile(new File(presetDir, name + ".json"), preset);
        CyvClientConfig.set("selectedPreset", name + ".json");
        ConfigLoader.save(CyvForge.config, false);
    }

    public static void savePresetToFile(File file, HUDPreset preset) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            new GsonBuilder().setPrettyPrinting().create().toJson(preset, writer);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void saveCurrentLayoutToSelected() {
        String selectedFileName = CyvClientConfig.getString("selectedPreset", "none");
        if (selectedFileName.equals("none") || selectedFileName.isEmpty()) return;

        File presetDir = new File(Minecraft.getMinecraft().mcDataDir, "config/cyvforge/presets");
        File file = new File(presetDir, selectedFileName);
        if (!file.exists()) return;

        HUDPreset preset;
        try (Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
            preset = new Gson().fromJson(reader, HUDPreset.class);
        } catch (Exception e) {
            preset = new HUDPreset(selectedFileName.replace(".json", ""));
        }

        for (DraggableHUDElement e : HUDManager.registeredRenderers) {
            ScreenPosition p = (e.position != null) ? e.position : e.load();
            if (p == null) p = e.getDefaultPosition();
            preset.positions.put(e.getName(), p.getAbsoluteX() + "," + p.getAbsoluteY() + "," + e.isVisible + "," + e.isEnabled);
        }

        savePresetToFile(file, preset);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        saveCurrentLayoutToSelected();
        super.onGuiClosed();
    }

    class PresetLine {
        File file; HUDPreset data; GuiTextField nameField; SubButton editBtn, deleteBtn;

        public PresetLine(File f) {
            this.file = f;
            try (Reader reader = new InputStreamReader(new FileInputStream(f), "UTF-8")) {
                this.data = new Gson().fromJson(reader, HUDPreset.class);
            } catch (Exception e) { this.data = new HUDPreset(f.getName().replace(".json", "")); }
            nameField = new GuiTextField(0, fontRendererObj, 0, 0, 110, 14);
            nameField.setText(data.presetName);
            nameField.setEnableBackgroundDrawing(true);
            editBtn = new SubButton("Edit", 0, 0, 40, 14);
            deleteBtn = new SubButton("X", 0, 0, 15, 14);
        }

        public void draw(int y, int mx, int my) {
            int xStart = width / 2 - sizeX / 2 + 5;
            nameField.xPosition = xStart; nameField.yPosition = y;
            editBtn.setX(xStart + 120); editBtn.setY(y);
            deleteBtn.setX(xStart + 165); deleteBtn.setY(y);
            nameField.drawTextBox(); editBtn.draw(mx, my); deleteBtn.draw(mx, my);
        }

        public void saveName() {
            String newName = nameField.getText().trim();
            if (newName.isEmpty() || newName.equals(data.presetName)) return;
            File newFile = new File(presetDir, newName + ".json");
            if (file.renameTo(newFile)) {
                if (CyvClientConfig.getString("selectedPreset", "").equals(file.getName())) {
                    CyvClientConfig.set("selectedPreset", newFile.getName());
                }
                this.file = newFile;
                data.presetName = newName;
                savePresetToFile(file, data);
            }
            nameField.setFocused(false);
            ConfigLoader.save(CyvForge.config, false);
        }

        public boolean mouseClicked(int y, int mx, int my, int btn) {
            int xStart = width / 2 - sizeX / 2 + 5;
            nameField.mouseClicked(mx, my, btn);
            if (mx >= xStart && mx <= xStart + sizeX && my >= y && my <= y + 16) {
                if (mx >= xStart + 165 && mx <= xStart + 185) {
                    boolean wasSelected = CyvClientConfig.getString("selectedPreset", "").equals(file.getName());
                    if (file.delete()) {
                        if (wasSelected) {
                            CyvClientConfig.set("selectedPreset", "none");
                        }
                        refreshPresets();
                    }
                    return true;
                }
                saveCurrentLayoutToSelected();
                CyvClientConfig.set("selectedPreset", file.getName());
                applyPreset();
                ConfigLoader.save(CyvForge.config, false);
                if (mx >= xStart + 120 && mx <= xStart + 160) {
                    mc.displayGuiScreen(new GuiHUDPositions(true, true));
                }
                return true;
            }
            return false;
        }

        public void applyPreset() {
            for (DraggableHUDElement e : HUDManager.registeredRenderers) {
                String dataStr = data.positions.get(e.getName());
                if (dataStr != null) {
                    String[] pts = dataStr.split(",");
                    e.save(new ScreenPosition(Integer.parseInt(pts[0]), Integer.parseInt(pts[1])));
                    e.load();
                    if (pts.length >= 3) e.isVisible = Boolean.parseBoolean(pts[2]);
                    if (pts.length >= 4) e.setEnabled(Boolean.parseBoolean(pts[3]));
                }
            }
        }
    }
}
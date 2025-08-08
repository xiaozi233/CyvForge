package net.cyvforge.gui.config;


public interface ConfigPanel {
    public boolean mouseInBounds(int mouseX, int mouseY);
    public void mouseClicked(int mouseX, int mouseY, int mouseButton);
    public void keyTyped(char typedChar, int keyCode);
    public void draw(int mouseX, int mouseY, int scroll);
    public default void update() {}
    public void mouseDragged(int mouseX, int mouseY);
    public void save();
    public default void select() {}
    public default void unselect() {}

    public default void onValueChange() {}

}
package net.cyvforge.hud.structure;

public interface IRenderer {
    int getWidth();
    int getHeight();

    public void save(ScreenPosition pos);

    public ScreenPosition load();

    void render(ScreenPosition pos);

    default void renderDummy(ScreenPosition pos) {
        render(pos);
    }

    default boolean renderInChat() {
        return true;
    }

    default boolean renderInGui() {
        return false;
    }

    default boolean renderInOverlay() {
        return false;
    }

    

}

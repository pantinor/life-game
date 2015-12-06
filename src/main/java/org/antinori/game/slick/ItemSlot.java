package org.antinori.game.slick;

import de.matthiasmann.twl.Alignment;
import de.matthiasmann.twl.ParameterMap;
import de.matthiasmann.twl.ThemeInfo;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.renderer.Image;

public class ItemSlot extends ToggleButton {

    private String item;
    private Image icon;
    private ParameterMap icons;

    public ItemSlot() {
        setAlignment(Alignment.TOP);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
        findIcon();
    }

    public Image getIcon() {
        return icon;
    }

    @Override
    public int getPreferredInnerWidth() {
        return 96;
    }

    @Override
    public int getPreferredInnerHeight() {
        return 96;
    }

    @Override
    protected void applyTheme(ThemeInfo themeInfo) {
        super.applyTheme(themeInfo);
        icons = themeInfo.getParameterMap("icons");
        findIcon();
        setOverlay(icon);
    }

    private void findIcon() {
        if (item == null || icons == null) {
            icon = null;
        } else {
            icon = icons.getImage(item);
        }
    }
}

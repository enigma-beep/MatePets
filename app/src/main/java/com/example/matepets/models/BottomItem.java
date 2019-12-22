package com.example.matepets.models;

public class BottomItem {
    private int itemId;
    private int itemIconId;
    private int itemFillIconId;
    private boolean isHasNotification = false;

    public BottomItem(int itemId, int itemIconId, int itemFillIconId, boolean isHasNotification) {
        this.itemId = itemId;
        this.itemIconId = itemIconId;
        this.itemFillIconId = itemFillIconId;
        this.isHasNotification = isHasNotification;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemIconId() {
        return itemIconId;
    }

    public void setItemIconId(int itemIconId) {
        this.itemIconId = itemIconId;
    }

    public int getItemFillIconId() {
        return itemFillIconId;
    }

    public void setItemFillIconId(int itemFillIconId) {
        this.itemFillIconId = itemFillIconId;
    }

    public boolean isHasNotification() {
        return isHasNotification;
    }

    public void setHasNotification(boolean hasNotification) {
        isHasNotification = hasNotification;
    }
}

package com.mhz.overlayview;

import android.content.res.TypedArray;


public class OverlayState {
	
	private static final int NO_ID = 0;
	
	public static final int ACTION_ALWAYS = 1;
	public static final int ACTION_WITH_TEXT = 2;
	public static final int ACTION_IF_ROOM = 3;
	public static final int ACTION_NEVER = 4;
	
	private final int defaultItemId = 0;
	private final int defaultShowAsAction = ACTION_ALWAYS;
	private final int defaultGroupId = 0;

	private int groupId;
	private boolean groupVisible;
	private boolean groupEnabled;
	private int groupOrderInCategory;
	//TODO checkable behaviour for group
	
	private int itemId;
	private String itemText;
	private String itemTextCondensed;
	private int itemIconId;
	private int itemShowAsAction;
	private int itemOrderInCategory;
	private boolean itemVisibility;
	private boolean itemEnable;


	
	public OverlayState() {
	}
	
	public void resetGroup() {
		groupId = NO_ID;
		groupVisible = true;
		groupEnabled = true;
		groupOrderInCategory = 0;
	}
	
	public void readItem(TypedArray a) {
		itemId = a.getInteger(R.styleable.OverlayItem_overlay_id, defaultItemId);
		itemText = a.getString(R.styleable.OverlayItem_overlay_title);
		itemTextCondensed = a.getString(R.styleable.OverlayItem_overlay_title_condensed);
		itemShowAsAction = a.getInteger(R.styleable.OverlayItem_overlay_showAsAction, defaultShowAsAction);
		itemOrderInCategory = a.getInteger(R.styleable.OverlayItem_overlay_order_in_category, 0);
		itemVisibility = a.getBoolean(R.styleable.OverlayItem_overlay_visible, true);
		itemEnable = a.getBoolean(R.styleable.OverlayItem_overlay_enabled, true);
	}
	
	public void readGroup(TypedArray a) {
		groupId = a.getInteger(R.styleable.OverlayGroup_overlay_group_id, defaultGroupId);
		groupVisible = a.getBoolean(R.styleable.OverlayGroup_overlay_group_visible, true);
		groupEnabled = a.getBoolean(R.styleable.OverlayGroup_overlay_group_enabled, true);
		groupOrderInCategory = a.getInteger(R.styleable.OverlayGroup_overlay_group_order_in_category, 0);
	}
	
	public OverlayState addItem() {
		return this;
	}
	
	@Override
	public String toString() {
		String s = "[group id: " + groupId + ", group enabled: " + groupEnabled + ", item id: " + itemId + ", item Text: " + itemText + "]";
		return s;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public boolean isGroupVisible() {
		return groupVisible;
	}

	public void setGroupVisible(boolean groupVisible) {
		this.groupVisible = groupVisible;
	}

	public boolean isGroupEnabled() {
		return groupEnabled;
	}

	public void setGroupEnabled(boolean groupEnabled) {
		this.groupEnabled = groupEnabled;
	}

	public int getGroupOrderInCategory() {
		return groupOrderInCategory;
	}

	public void setGroupOrderInCategory(int groupOrderInCategory) {
		this.groupOrderInCategory = groupOrderInCategory;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemText() {
		return itemText;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	public String getItemTextCondensed() {
		return itemTextCondensed;
	}

	public void setItemTextCondensed(String itemTextCondensed) {
		this.itemTextCondensed = itemTextCondensed;
	}

	public int getItemIconId() {
		return itemIconId;
	}

	public void setItemIconId(int itemIconId) {
		this.itemIconId = itemIconId;
	}

	public int getItemShowAsAction() {
		return itemShowAsAction;
	}

	public void setItemShowAsAction(int itemShowAsAction) {
		this.itemShowAsAction = itemShowAsAction;
	}

	public int getItemOrderInCategory() {
		return itemOrderInCategory;
	}

	public void setItemOrderInCategory(int itemOrderInCategory) {
		this.itemOrderInCategory = itemOrderInCategory;
	}

	public boolean isItemVisibility() {
		return itemVisibility;
	}

	public void setItemVisibility(boolean itemVisibility) {
		this.itemVisibility = itemVisibility;
	}

	public boolean isItemEnable() {
		return itemEnable;
	}

	public void setItemEnable(boolean itemEnable) {
		this.itemEnable = itemEnable;
	}
}

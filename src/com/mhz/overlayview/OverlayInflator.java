package com.mhz.overlayview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Xml;

public class OverlayInflator {

	private static final String TAG = "OverlayInflator";
	private static final Object XML_OVERLAY = "overlay";
	private static final Object OVERLAY_GROUP = "group";
	private static final Object OVERLAY_ITEM = "item";
	private Context context;

	public OverlayInflator(Context context) {
		this.context = context;
	}

	public List<OverlayState> inflate(int res) {
		try {
			XmlPullParser parser = context.getResources().getXml(res);
			AttributeSet attrs = Xml.asAttributeSet(parser);
			return parseXml(parser, attrs);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<OverlayState> parseXml(XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
		int eventType = parser.getEventType();
		String tagName;
		boolean lookingForEndOfUnknownTag = false;
		String unknownTagName = null;
		
		List<OverlayState> overlayStates = new ArrayList<OverlayState>();
		
		// This loop will skip to the menu start tag
		do {
			if (eventType == XmlPullParser.START_TAG) {
				tagName = parser.getName();
				if (tagName.equals(XML_OVERLAY)) {
					// Go to next tag
					eventType = parser.next();
					break;
				}

				throw new RuntimeException("Expecting menu, got " + tagName);
			}
			eventType = parser.next();
		} while (eventType != XmlPullParser.END_DOCUMENT);
		
		OverlayState overlayState = new OverlayState();
		
		boolean reachedEndOfMenu = false;
        while (!reachedEndOfMenu) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (lookingForEndOfUnknownTag) {
                        break;
                    }
                    
                    tagName = parser.getName();

                    if (tagName.equals(OVERLAY_GROUP)) {
                    	TypedArray a = context.obtainStyledAttributes(attrs,
                				R.styleable.OverlayItem);
                    	overlayState.readGroup(a);
                    } else if (tagName.equals(OVERLAY_ITEM)) {
                    	TypedArray a = context.obtainStyledAttributes(attrs,
                				R.styleable.OverlayItem);
                    	int titl = a.getInt(R.styleable.OverlayItem_overlay_id, -1);
                    	overlayState.readItem(a);
                    } else {
                        lookingForEndOfUnknownTag = true;
                        unknownTagName = tagName;
                    }
                    break;
                    
                case XmlPullParser.END_TAG:
                    tagName = parser.getName();
                    if (lookingForEndOfUnknownTag && tagName.equals(unknownTagName)) {
                        lookingForEndOfUnknownTag = false;
                        unknownTagName = null;
                    } else if (tagName.equals(OVERLAY_GROUP)) {
                    	overlayState.resetGroup();
                    } else if (tagName.equals(OVERLAY_ITEM)) {
                    	//TODO this might have sub items - deal with it
                    	overlayStates.add(overlayState.addItem());
                        // Add the item if it hasn't been added (if the item was
                        // a submenu, it would have been added already)
//                        if (!menuState.hasAddedItem()) {
//                            if (menuState.itemActionProvider != null &&
//                                    menuState.itemActionProvider.hasSubMenu()) {
//                                menuState.addSubMenuItem();
//                            } else {
//                                menuState.addItem();
//                            }
//                        }
                    } else if (tagName.equals(XML_OVERLAY)) {
                        reachedEndOfMenu = true;
                    }
                    break;
                    
                case XmlPullParser.END_DOCUMENT:
                    throw new RuntimeException("Unexpected end of document");
            }
            eventType = parser.next();
        }
        
        
        return overlayStates;
	}

}

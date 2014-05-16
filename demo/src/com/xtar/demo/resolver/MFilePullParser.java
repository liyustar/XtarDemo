package com.xtar.demo.resolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.xtar.demo.model.MFile;

public class MFilePullParser {

    public List<MFile> doParse(InputStream in) {
        List<MFile> mfiles = null;
        MFile mfile = null;
        String tagName = "";

        XmlPullParser parser = Xml.newPullParser();

        try {
            parser.setInput(in, "utf-8");

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        mfiles = new ArrayList<MFile>();
                        break;
                    case XmlPullParser.START_TAG:
                        tagName = parser.getName();
                        if ("person".equals(tagName)) {
                            int id = Integer.valueOf(parser.getAttributeValue(0));
                            mfile = new MFile(id);
                        } else if ("name".equals(tagName)) {
                            mfile.name = parser.nextText();
                        } else if ("len".equals(tagName)) {
                            mfile.len = Integer.valueOf(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if ("person".equals(tagName)) {
                            mfiles.add(mfile);
                        }
                        break;
                    default:
                        break;
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mfiles;
    }

}

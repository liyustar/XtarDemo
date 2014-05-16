package com.xtar.demo.resolver;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xtar.demo.module.MFile;

public class MFileSaxParser {

    private SAXParser parser;

    public MFileSaxParser() {
        SAXParserFactory f = SAXParserFactory.newInstance();
        try {
            parser = f.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<MFile> doParse(InputStream in) {
        XmlHandler h = new XmlHandler();
        try {
            parser.parse(in, h);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return h.getMFiles();
    }

    class XmlHandler extends DefaultHandler {
        List<MFile> mfiles = null;
        private MFile mfile;
        private Object currentEleName = "";

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            mfiles = new ArrayList<MFile>();
        }

        @Override
        public void endDocument() throws SAXException {
            // TODO Auto-generated method stub
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            if ("person".equals(localName)) {
                int id = Integer.valueOf(attributes.getValue("id"));
                mfile = new MFile(id);
            } else {
                // name len
                currentEleName = localName;
            }
            super.startElement(uri, localName, qName, attributes);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("person".equals(localName)) {
                mfiles.add(mfile);
            } else {
                // name len
                currentEleName = "";
            }
            super.endElement(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start, length);
            if ("name".equals(currentEleName)) {
                mfile.name = str;
            } else if ("len".equals(currentEleName)) {
                mfile.len = Integer.valueOf(str);
            }
            super.characters(ch, start, length);
        }

        public List<MFile> getMFiles() {
            return mfiles;
        }

    }

}

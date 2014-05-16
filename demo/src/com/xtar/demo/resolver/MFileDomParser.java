package com.xtar.demo.resolver;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xtar.demo.model.MFile;

public class MFileDomParser {

    private DocumentBuilder builder;
    private MFile mfile;

    public MFileDomParser() {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        try {
            builder = f.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<MFile> doParse(InputStream in) {
        List<MFile> mfiles = new ArrayList<MFile>();
        Document doc;
        try {
            doc = builder.parse(in);
            NodeList list = doc.getElementsByTagName("person");
            for (int i = 0, len = list.getLength(); i < len; i++) {
                Element el = (Element) list.item(i);
                int id = Integer.valueOf(el.getAttribute("id"));
                mfile = new MFile(id);
                mfile.name = GetSubelementTextContentByName(el, "name");
                mfile.len = Integer.valueOf(GetSubelementTextContentByName(el, "len"));

                mfiles.add(mfile);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mfiles;
    }

    private String GetSubelementTextContentByName(Element el, String name) {
        NodeList list = el.getElementsByTagName(name);
        Element e = (Element) list.item(0);
        return e.getTextContent();
    }

}

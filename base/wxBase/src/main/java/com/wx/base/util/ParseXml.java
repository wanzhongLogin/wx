package com.wx.base.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析xml文件
 */
public class ParseXml {

    /**
     * 解析微信发送的xml文件
     * @param is
     * @return
     * @throws DocumentException
     */
    public static Map<String,String> parseXml(InputStream is) throws DocumentException, IOException {
        //返回结果
        Map<String,String> map = new HashMap();

        //读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        //得到xml根元素
        Element root = document.getRootElement();
        //获取所有元素
        List<Element> elements = root.elements();
        for(Element element : elements) {
            map.put(element.getName(),element.getText());
        }

        //释放资源
        is.close();
        is = null;

        return map;
    }

}

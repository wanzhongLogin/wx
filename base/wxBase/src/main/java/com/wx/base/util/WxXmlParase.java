package com.wx.base.util;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;


/**
 * XmlRootElement:可以将该类转换成xml文件,也可以进行解析
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxXmlParase extends WxMpInMemoryConfigStorage {

    /**
     * 解析xml文件信息
     */
    public static WxXmlParase forXml(InputStream is) throws JAXBException {
        Unmarshaller um = JAXBContext.newInstance(WxXmlParase.class).createUnmarshaller();
        InputSource inputSource = new InputSource(is);
        inputSource.setEncoding("UTF-8");
        WxXmlParase wxXmlParase = (WxXmlParase) um.unmarshal(inputSource);
        return wxXmlParase;
    }

}

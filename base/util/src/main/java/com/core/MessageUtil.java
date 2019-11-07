package com.core;

import com.annotation.XstreamCDATA;
import com.model.Article;
import com.model.Type;
import com.resMsg.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息处理工具类
 *
 * @author liufeng
 * @date 2013-09-15
 */
public class MessageUtil {
    // 请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：语音
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：视频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    // 请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    // 请求消息类型：事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    // 事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 事件类型：scan(用户已关注时的扫描带参数二维码)
    public static final String EVENT_TYPE_SCAN = "scan";
    // 事件类型：LOCATION(上报地理位置)
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    // 事件类型：CLICK(自定义菜单)
    public static final String EVENT_TYPE_CLICK = "CLICK";

    // 响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    // 响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    // 响应消息类型：语音
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    // 响应消息类型：视频
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    // 响应消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    // 响应消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";


    private static XStream xstream2 = new XStream();

    /**
     * 扩展xstream使其支持CDATA
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {

                boolean cdata = false;

                List<String> list = new ArrayList<>();
                /**
                 * @param name 字段名称
                 * @param clazz 每一个字段对应的class
                 */
                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                    if(!name.equals("xml")){
                        cdata = list.contains(name);
                    }else{
                        try {
                            list = initField(clazz);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[" + text + "]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    /**
     * 文本消息对象转换成xml
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String messageToXml(TextMessageRes textMessage) {
        xstream.alias("xml", textMessage.getClass());
        xstream2.processAnnotations(textMessage.getClass());
        String result = xstream2.toXML(textMessage);
        return result;
    }

    /**
     * 解析微信发来的请求（XML）
     * @param request
     * @return Map<String, String>
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList){
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }


    /**
     * 图片消息对象转换成xml
     * @param imageMessage 图片消息对象
     * @return xml
     */
    public static String messageToXml(ImageMessageRes imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 语音消息对象转换成xml
     * @param voiceMessage 语音消息对象
     * @return xml
     */
    public static String messageToXml(VoiceMessageRes voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成xml
     * @param videoMessage 视频消息对象
     * @return xml
     */
    public static String messageToXml(VideoMessageRes videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐消息对象转换成xml
     *
     * @param musicMessage 音乐消息对象
     * @return xml
     */
    public static String messageToXml(MusicMessageRes musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成xml
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String messageToXml(NewsMessageRes newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }





    private static void add(Field[] fields,List<String> list){
        for (Field f : fields) {
            XstreamCDATA annotation = f.getAnnotation(XstreamCDATA.class);
            if(annotation != null){
                list.add(f.getName());
            }
        }
    }
    /**
     * 获取类的所有字段,需要cdata的
     * @param clazz
     * @return
     */
    private static List<String> initField(Class clazz) throws Exception {

        List<String> list = new ArrayList<>();

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {

            Class<?> type = field.getType();
            if(Type.class.isAssignableFrom(type)){
                Field[] fields = type.getDeclaredFields();
                add(fields,list);
            }

            if(List.class.isAssignableFrom(type)){
                java.lang.reflect.Type genericType = field.getGenericType();
                if(genericType == null){
                    continue;
                }
                if(genericType instanceof ParameterizedType){
                    ParameterizedType pt = (ParameterizedType) genericType;
                    //得到泛型里的class类型对象
                    Class<?> fx = (Class<?>)pt.getActualTypeArguments()[0];
                    if(Type.class.isAssignableFrom(fx)){
                        Field[] declaredFields1 = fx.getDeclaredFields();
                        add(declaredFields1,list);
                    }
                }

            }

            XstreamCDATA annotation = field.getAnnotation(XstreamCDATA.class);
            if(annotation != null){
                list.add(field.getName());
            }
        }

        clazz = clazz.getSuperclass();
        while(!clazz.equals(Object.class)){
            Field[] fields = clazz.getDeclaredFields();
            add(fields,list);
            clazz = clazz.getSuperclass();
        }
        return list;
    }
}

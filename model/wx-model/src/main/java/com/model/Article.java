package com.model;

import com.annotation.XstreamCDATA;
import lombok.Data;

/**
 * 图文model
 */
@Data
public class Article extends Type{

    //图文消息名称
    @XstreamCDATA
    private String Title;

    //图文消息描述
    @XstreamCDATA
    private String Description;

    //图片链接,jpg,png
    @XstreamCDATA
    private String PicUrl;

    //点击图片消息,跳转链接
    @XstreamCDATA
    private String Url;

}

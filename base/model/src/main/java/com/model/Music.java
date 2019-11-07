package com.model;

import com.annotation.XstreamCDATA;
import lombok.Data;

/**
 * 音乐mode
 */
@Data
public class Music extends Type{
    //音乐标题
    @XstreamCDATA
    private String Title;
    //音乐描述
    @XstreamCDATA
    private String Description;
    //音乐链接
    @XstreamCDATA
    private String MusicUrl;
    //高品质的音乐链接
    @XstreamCDATA
    private String HqMusicUrl;
    //缩略图的媒体id
    @XstreamCDATA
    private String ThumbMediaId;

}

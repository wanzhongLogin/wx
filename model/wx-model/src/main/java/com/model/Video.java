package com.model;

import com.annotation.XstreamCDATA;
import lombok.Data;

/**
 * 视频model
 */
@Data
public class Video extends Type{

    //媒体文件Id
    @XstreamCDATA
    private String MediaId;

    //缩略图的媒体id
    @XstreamCDATA
    private String ThumbMediaId;
}

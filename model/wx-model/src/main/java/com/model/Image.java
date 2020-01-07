package com.model;

import com.annotation.XstreamCDATA;
import lombok.Data;

/**
 * 图片model
 */
@Data
public class Image extends Type{

    //媒体文件id
    @XstreamCDATA
    private String MediaId;

}

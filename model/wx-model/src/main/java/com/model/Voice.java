package com.model;

import com.annotation.XstreamCDATA;
import lombok.Data;

/**
 * 语音model
 */
@Data
public class Voice extends Type{

    //媒体文件id
    @XstreamCDATA
    private String MediaId;

}

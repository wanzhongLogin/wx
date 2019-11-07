package com.button;

import lombok.Data;

/**
 * 点击按钮
 * @author wan
 */
@Data
public class ClickButton extends BaseButton{

    //按钮类型
    private String type;
    //按钮键值
    private String key;

}

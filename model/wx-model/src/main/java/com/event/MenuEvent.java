package com.event;

import lombok.Data;

/**
 * 自定义菜单事件
 * @author wan
 */
@Data
public class MenuEvent extends BaseEvent{

    //时间key,与自定义菜单接口中key值对应
    private String eventKey;

}

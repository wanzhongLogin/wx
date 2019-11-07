package com.button;

import lombok.Data;

/**
 * 复合按钮
 */
@Data
public class ComplexButton extends BaseButton{

    private BaseButton[] sub_button;

}

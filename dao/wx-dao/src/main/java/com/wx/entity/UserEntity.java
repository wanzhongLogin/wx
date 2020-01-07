package com.wx.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 是否激活注册
     * 第一次关注的  1未激活  填写基本信息,2已激活 退订阅3
     */
    private Integer status;


    /**
     * 是否订阅公众号
     * 1:订阅
     * 0:未
     */
    @Column(name = "subscribe")
    private String subscribe;

    /**
     * 唯一标识
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 性别:值为1时是男性，值为2时是女性，值为0时是未知
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    @Column(name = "language")
    private String language;


    /**
     * 用户所在城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 用户所在省
     */
    @Column(name = "province")
    private String province;

    /**
     * 用户所在国
     */
    @Column(name = "country")
    private String country;

    /**
     * 头像链接
     */
    @Column(name = "headimg_url")
    private String headimgUrl;

    /**
     * 用户关注时间，为时间戳
     * 如果用户曾多次关注，则取最后关注时间
     */
    @Column(name = "subscribe_time")
    private String subscribeTime;


    /**
     * 返回用户关注的渠道来源，
     * ADD_SCENE_SEARCH 公众号搜索，
     * ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，
     * ADD_SCENE_PROFILE_CARD 名片分享，
     * ADD_SCENE_QR_CODE 扫描二维码，
     * ADD_SCENE_PROFILE_ LINK 图文页内名称点击，
     * ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，
     * ADD_SCENE_PAID 支付后关注，
     * ADD_SCENE_OTHERS 其他
     */
    @Column(name = "subscribe_scene")
    private String subscribeScene;

    /**
     * 在该平台,生一个好看的id
     */
    private String code;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

}

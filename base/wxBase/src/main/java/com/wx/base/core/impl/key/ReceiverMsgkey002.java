package com.wx.base.core.impl.key;


import com.BaseMessage;
import com.wx.base.util.MessageUtil;
import com.model.Music;
import com.resMsg.MusicMessageRes;
import com.wx.base.core.ReceiverMsg;
import org.springframework.stereotype.Component;


/**
 * key002按钮事件
 */
@Component("receiverMsgkey002")
public class ReceiverMsgkey002 extends ReceiverMsg {

    @Override
    public String parseRequest(BaseMessage bm) {

        Music music = new Music();

        music.setTitle("li");
        music.setDescription("description");
        music.setMusicUrl("1");
        music.setHqMusicUrl("");
        music.setThumbMediaId("OQe6Ttxt-Ni5je9shrxwHzLkL1lPZ0vL0UOMXiV4c6s51yfyVBrJd359MOJvr1SV");

        MusicMessageRes musicMessageRes = new MusicMessageRes();
        musicMessageRes.setToUserName(bm.getToUserName());
        musicMessageRes.setFromUserName(bm.getFromUserName());
        musicMessageRes.setCreateTime(bm.getCreateTime());
        musicMessageRes.setMusic(music);

        System.out.println(MessageUtil.messageToXml(musicMessageRes));
        return MessageUtil.messageToXml(musicMessageRes);
    }

}







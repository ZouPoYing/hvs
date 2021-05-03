package com.graduation.hvs.service;

import com.graduation.hvs.dao.Msg;
import com.graduation.hvs.mapper.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MsgService {

    @Autowired(required = false)
    private MsgMapper msgMapper;

    public List<Msg> queryAllMsg() throws Exception {
        return msgMapper.queryAllMsg();
    }

    public void addMsg(Integer auditid, String msg, Integer msgtype) throws Exception {
        msgMapper.addMsg(auditid,msg,msgtype);
    }

    public List<Map<String, Object>> queryMsg1(Integer userid) throws Exception {
        return msgMapper.queryMsg1(userid);
    }

    public List<Map<String, Object>> queryMsg2(Integer userid) throws Exception {
        return msgMapper.queryMsg2(userid);
    }

    public List<Map<String, Object>> queryMsg11(Integer userid) throws Exception {
        return msgMapper.queryMsg11(userid);
    }

    public List<Map<String, Object>> queryMsg12(Integer userid) throws Exception {
        return msgMapper.queryMsg12(userid);
    }

    public void UpdateMsgUse(Integer msgid) throws Exception {
        msgMapper.UpdateMsgUse(msgid);
    }

    public void addMsg2(Integer auditid, String msg, Integer orderid) throws Exception {
        msgMapper.addMsg2(auditid,msg,orderid);
    }

    public void addMsgHvs(String msg, Integer msgtype, Integer processid, Integer patient, Integer doctor) throws Exception {
        msgMapper.addMsgHvs(msg,msgtype,processid,patient,doctor);
    }
    public List<Map<String, Object>> getReception(Integer doctor) throws Exception {
        return msgMapper.getReception(doctor);
    }
}

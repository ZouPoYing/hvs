package com.graduation.hvs.service;

import com.graduation.hvs.dao.Audit;
import com.graduation.hvs.mapper.AuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuditService {

    @Autowired(required = false)
    private AuditMapper auditMapper;

    public List<Audit> queryAllAudit() throws Exception {
        return auditMapper.queryAllAudit();
    }

    public void addAuditType1(Integer audittype, Integer committerid, Integer fileid, String company, String address) throws Exception {
        auditMapper.addAuditType1(audittype,committerid,fileid,company,address);
    }

    public void addAuditType2(Integer committerid, Integer fileid, Integer orderid) throws Exception {
        auditMapper.addAuditType2(committerid,fileid,orderid);
    }

    public List<Audit> queryAuditByAudittype(Integer audittype) throws Exception {
        return auditMapper.queryAuditByAudittype(audittype);
    }

    public void AuditType1(Integer auditid, Integer auditerid, String state) throws Exception {
        auditMapper.AuditType1(auditid,auditerid,state);
    }

    public void AuditType2(Integer auditid, Integer auditerid, String state) throws Exception {
        auditMapper.AuditType2(auditid,auditerid,state);
    }

    public Map<String, Object> checkAuditType1(Integer userid) throws Exception {
        return auditMapper.checkAuditType1(userid);
    }

    public void modifyAuditType1(Integer fileid,Integer auditid,String company, String address) throws Exception {
        auditMapper.modifyAuditType1(fileid,auditid,company,address);
    }

    public void modifyAuditType2(Integer fileid,Integer auditid) throws Exception {
        auditMapper.modifyAuditType2(fileid,auditid);
    }

    public void modifyAuditType1NoFileid(Integer auditid,String company, String address) throws Exception {
        auditMapper.modifyAuditType1NoFileid(auditid,company,address);
    }

    public void modifyAuditType2NoFileid(Integer auditid) throws Exception {
        auditMapper.modifyAuditType2NoFileid(auditid);
    }

    public Audit getAuditByAuditid(Integer auditid) throws Exception {
        return auditMapper.getAuditByAuditid(auditid);
    }

    public void addAuditType3(Integer committerid, Integer orderid, String reason) throws Exception {
        auditMapper.addAuditType3(committerid,orderid,reason);
    }

    public Audit getCurrentAudit() throws Exception {
        return auditMapper.queryAllAudit().get(0);
    }

    public List<Map<String, Object>> getAuditType3() throws Exception {
        return auditMapper.getAuditType3();
    }

    public void updateAuditStateAndReason(String reason,Integer auditid) throws Exception {
        auditMapper.updateAuditStateAndReason(reason,auditid);
    }
}

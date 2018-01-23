package com.nexusinfo.mia_muslimindustrialistassociation.models;

import java.io.Serializable;

/**
 * Created by firdous on 11/28/2017.
 */

public class UserModel implements Serializable{

    private String memberID, memberMobile, memberEmail, memberName, cmpId, brCode;

    public UserModel() {
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCmpId() {
        return cmpId;
    }

    public void setCmpId(String cmpId) {
        this.cmpId = cmpId;
    }

    public String getBrCode() {
        return brCode;
    }

    public void setBrCode(String brCode) {
        this.brCode = brCode;
    }
}

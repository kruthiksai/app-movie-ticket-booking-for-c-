package com.kruthik.scanner;

public class items {
    String MOVIENAME;
    String Name;
    String Code;
    String verified;

    public items(String MOVIENAME, String name, String code, String verified) {
        this.MOVIENAME = MOVIENAME;
        Name = name;
        Code = code;
        this.verified = verified;
    }

    public String getMOVIENAME() {
        return MOVIENAME;
    }

    public void setMOVIENAME(String MOVIENAME) {
        this.MOVIENAME = MOVIENAME;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }
}

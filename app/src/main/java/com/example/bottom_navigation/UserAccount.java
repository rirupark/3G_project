package com.example.bottom_navigation;


/**
 * 사용자 계정 정보 모델 클래스
 */
public class UserAccount {

    private String idToken; // Firebase Uid(고유 토튼정보(프라이머리 키))
    private String emailId;  // 이메일 아이디
    private String std_grade_num;

    private String className;
    private String tongArea;
    private String credit;
    private String area;


    public UserAccount() { } // 빈 생성자를 선언해줘야 firebase에 값을 가져오고 넣을 때, 오류가 생기지 않음

    public String getIdToken() {return idToken;}

    public void setIdToken(String idToken) {this.idToken =idToken;}

    public String getEmailId() { return emailId;}

    public void setEmailId(String emailId) {this.emailId = emailId;}

    public String getStd_grade_num() { return std_grade_num;}

    public void setStd_grade_num(String std_grade_num) {this.std_grade_num = std_grade_num;}

    public String getClassName() {return className;}

    public void  setClassName(String className) {this.className = className;}

    public String getTongArea() {return tongArea;}

    public void  setTongArea(String tongArea) {this.tongArea = tongArea;}

    public String getCredit() {return credit;}

    public void  setCredit(String credit) {this.credit = credit;}

    public String getArea() {return area;}

    public void setArea(String area) {this.area = area;}




}

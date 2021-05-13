package com.example.bottom_navigation;


/**
 * 사용자 계정 정보 모델 클래스
 */
public class UserChange {

    private String idToken; // Firebase Uid(고유 토큰정보(프라이머리 키))
    private String emailId;  // 이메일 아이디


    public UserChange() { } // 빈 생성자를 선언해줘야 firebase에 값을 가져오고 넣을 때, 오류가 생기지 않음

    public String getIdToken() {return idToken;}

    public void setIdToken(String idToken) {this.idToken =idToken;}

    public String getEmailId() { return emailId;}

    public void setEmailId(String emailId) {this.emailId = emailId;}

}

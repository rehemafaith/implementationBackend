package io.gynacare.gynacare.login;

import java.math.BigDecimal;

public class LoginBean {
    private BigDecimal loginId;
	private String loginUsername;
	private String loginPassword;
	private String loginRank;
    public BigDecimal getLoginId() {
        return loginId;
    }
    public void setLoginId(BigDecimal loginId) {
        this.loginId = loginId;
    }
    public String getLoginUsername() {
        return loginUsername;
    }
    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }
    public String getLoginPassword() {
        return loginPassword;
    }
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    public String getLoginRank() {
        return loginRank;
    }
    public void setLoginRank(String loginRank) {
        this.loginRank = loginRank;
    }

}

package com.shaneen.blog.blog.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

    @Size(min=5, max=30, message = "Username should contain 5 to 30 characters")
    private String userName;

    @NotNull
    @Size(min=5, max=50)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

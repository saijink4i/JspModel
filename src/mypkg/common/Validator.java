package mypkg.common;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
    //유효성 검사를 위한 메소드
    public boolean validate(HttpServletRequest request);
}
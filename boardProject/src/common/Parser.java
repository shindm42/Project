package common;

import static constants.Constants.MEMBER_HISTORY_EVENT_JOIN;
import static constants.Constants.MEMBER_HISTORY_EVENT_LEAVE;
import static constants.Constants.MEMBER_HISTORY_EVENT_LOGIN;
import static constants.Constants.MEMBER_HISTORY_EVENT_LOGOUT;
import static constants.Constants.MEMBER_HISTORY_EVENT_UPDATE;

public class Parser {
    public static String parseMemberEventType(int num) {
        String event = "";
        switch (num) {
            case MEMBER_HISTORY_EVENT_JOIN:
                event = "회원가입";
                break;
            case MEMBER_HISTORY_EVENT_UPDATE:
                event = "회원정보수정";
                break;
            case MEMBER_HISTORY_EVENT_LEAVE:
                event = "회원탈퇴";
                break;
            case MEMBER_HISTORY_EVENT_LOGIN:
                event = "로그인";
                break;
            case MEMBER_HISTORY_EVENT_LOGOUT:
                event = "로그아웃";
                break;
        }
        return event;
    }

    public static String chgToStr(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("'", "&#039;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }

    public static String chgToHTML(String str) {
        str = str.replaceAll("&amp;", "&");
        str = str.replaceAll("&lt;", "<");
        str = str.replaceAll("&gt;", ">");
        str = str.replaceAll("&quot;", "\"");
        return str;
    }
}

package top.keng.anime.utils;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 响应结果生成工具
 *
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
public class ResultListGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    private static final int Duplicate_LoginName = 600;
    private static final int USER_NOT_EXIST_CODE = 601;
    private static final int WRONG_PASSWORD_CODE = 602;
    private static final int Undefined_Error = 603;

    public static ResultList genSuccessResult() {
        ResultList result = new ResultList();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static ResultList genSuccessResult(String message) {
        ResultList result = new ResultList();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);
        return result;
    }

    public static ResultList genSuccessResult(String message, List data) {
        ResultList result = new ResultList();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static ResultList genFailResult(String message) {
        ResultList result = new ResultList();
        result.setResultCode(RESULT_CODE_SERVER_ERROR);
        if (StringUtils.isEmpty(message)) {
            result.setMessage(DEFAULT_FAIL_MESSAGE);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static ResultList genErrorResult(int code, String message) {
        ResultList result = new ResultList();
        result.setResultCode(code);
        result.setMessage(message);
        return result;
    }
}

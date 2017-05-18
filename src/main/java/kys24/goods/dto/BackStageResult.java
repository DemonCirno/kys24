package kys24.goods.dto;

import kys24.goods.enums.ResultEnum;
import kys24.goods.exception.ResultException;

/**
 * @author Duolaimon
 *         17-5-1 下午7:45
 */
public class BackStageResult<T> {
    /** 状态码 */
    private int code;
    /** 状态消息 */
    private String message;
    /** data*/
    private T data;


    public BackStageResult(ResultEnum resultEnum, T commodity) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = commodity;
    }

    public BackStageResult(ResultException e, T data) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

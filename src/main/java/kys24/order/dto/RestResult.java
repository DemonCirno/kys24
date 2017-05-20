package kys24.order.dto;

/**
 * Created by cirno on 2017/5/19.
 */
public class RestResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public RestResult(){}

    public RestResult(Integer code,String message,T date){
        this.code = code;
        this.message = message;
        this.data = date;
    }
    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

package kys24.goods.enums;

/**
 * @author Duolaimon
 *         17-5-1 下午8:43
 */
public enum ResultEnum {
    SUCCESS(100,"操作成功"),
    DUPLICATE_INSERT(101,"插入失败,id已存在"),
    UPDATE_NOT_EXIST_ID(102,"更新失败,不存在指定id"),
    DELETE_NOT_EXIST_ID(103,"删除失败,不存在指定id"),
    SELECT_NOT_EXIST_ID(104,"查找失败,不存在指定id"),
    UPLOAD_EMPTY_FILE(111, "无法上传空文件"),
    FILE_TOO_LARGE(112, "文件过大,无法上传"),
    OTHERS_EXCEPTION(-1,"出现未知异常");

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

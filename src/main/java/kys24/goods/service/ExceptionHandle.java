package kys24.goods.service;

import kys24.goods.dto.BackStageResult;
import kys24.goods.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

/**
 * @author Duolaimon
 *         17-5-4 下午10:45
 */
@ControllerAdvice
public class ExceptionHandle {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BackStageResult<String> handle(Exception e) {
        if (e instanceof MultipartException) {
            return new BackStageResult<>(ResultEnum.FILE_TOO_LARGE, null);
        }
        logger.error("Exception:{}",e.getMessage());
        return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);

    }
}

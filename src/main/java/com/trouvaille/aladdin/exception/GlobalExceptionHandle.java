package com.trouvaille.aladdin.exception;


import com.trouvaille.aladdin.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;


@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandle {


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandle(final SQLIntegrityConstraintViolationException ex) {
        GlobalExceptionHandle.log.error(ex.getMessage());

        if (ex.getMessage().contains("Duplicate entry")) {
            final String[] split = ex.getMessage().split(" ");
            final String msg = split[2] + "已存在!";
            return R.error(msg);
        }
        return R.error("操作失败!请检查重试!");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> customExceptionHandle(final CustomException ex) {
        GlobalExceptionHandle.log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public R<String> fileNotFoundExceptionHandle(final FileNotFoundException ex) {
        GlobalExceptionHandle.log.error(ex.getMessage());
        return R.error(ex.getMessage());

    }
}

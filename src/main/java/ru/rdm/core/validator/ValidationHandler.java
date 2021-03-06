package ru.rdm.core.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rdm.core.controller.dto.support.ErrorRes;
import ru.rdm.core.controller.util.ControllerUtil;
import ru.rdm.core.enums.Error;
import ru.rdm.core.exception.RdmException;

@Component
@Slf4j
public class ValidationHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorRes> handleException(Exception ex) {
        log.error("error in service", ex);
        if (ex instanceof RdmException){
            RdmException e = (RdmException) ex;
            Error error = e.getError();
            return ControllerUtil.error(error);
        }
        return ControllerUtil.error(Error.PROHIBITED);
    }

}

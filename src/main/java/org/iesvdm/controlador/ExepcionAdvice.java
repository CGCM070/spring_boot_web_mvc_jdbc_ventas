package org.iesvdm.controlador;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExepcionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public String handleAllUncaughtExeption(Model model , RuntimeException  runtimeException){

        model.addAttribute("traza" , runtimeException.getMessage());

        return "error";

    }


}

package com.anjarul.toDo.configurations;


import com.anjarul.toDo.helperUtilities.CustomDateFormat;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

@ControllerAdvice
public class CustomDateEditorRegistrer implements PropertyEditorRegistrar {

    @InitBinder
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(Date.class, new CustomDateEditor(new CustomDateFormat(), true));
    }

}

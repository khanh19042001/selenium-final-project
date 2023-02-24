package com.misa.annotations;


import com.misa.constants.AuthorType;
import com.misa.constants.CategoryType;

import javax.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//A Custom Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MisaAnnotation {
    public CategoryType[] category();
    public AuthorType[] author();
    @Nullable
    public AuthorType[] reviewer();
}

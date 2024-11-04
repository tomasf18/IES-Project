package sts.backend.core_app.config;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimescaleTable {
    String tableName();

    String timeColumnName();
}

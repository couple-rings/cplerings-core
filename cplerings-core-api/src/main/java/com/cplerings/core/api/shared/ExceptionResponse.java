package com.cplerings.core.api.shared;

import java.io.PrintWriter;
import java.io.StringWriter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse extends AbstractResponse {

    private String stackTrace;
    private Type type;

    public ExceptionResponse(Exception e) {
        this.stackTrace = getStackTraceAsString(e);
        this.type = Type.EXCEPTION;
    }

    private String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}

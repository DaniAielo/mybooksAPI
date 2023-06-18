package br.com.jlgregorio.mybooks.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomExceptionResponse {

    private Date timeStamp;
    private String message;
    private String details;

}

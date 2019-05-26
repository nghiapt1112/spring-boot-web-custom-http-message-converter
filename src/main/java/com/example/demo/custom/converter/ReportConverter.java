package com.example.demo.custom.converter;

import com.example.demo.AbstractObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;

public class ReportConverter<E extends AbstractObject> extends AbstractHttpMessageConverter<E> {

    private static final ObjectMapper mapper = new ObjectMapper();

    public ReportConverter() {
        super(new MediaType("text", "report"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return AbstractObject.class.isAssignableFrom(clazz);
    }

    @Override
    protected E readInternal(Class<? extends E> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return mapper.readValue(inputMessage.getBody(), clazz);
    }

    @Override
    protected void writeInternal(E e, HttpOutputMessage outputMessage) throws HttpMessageNotWritableException {
        try {
            OutputStream outputStream = outputMessage.getBody();
            outputStream.write(e.toString().getBytes());
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

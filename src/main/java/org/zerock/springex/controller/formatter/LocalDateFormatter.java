package org.zerock.springex.controller.formatter;

//Formats objects of type T. A Formatter is both a Printer and a Parser for an object type.
//문자열을 객체로, 객체를 문자로
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//변환은 주로 웹 애플리케이션에서 클라이언트와 서버 사이의 데이터 전송에서 유용하게 사용됩니다.
public class LocalDateFormatter implements Formatter<LocalDate> {
//    @Override //문자열을 localdate 객체로
//    public LocalDate parse(String text, Locale locale) throws ParseException {
//        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
//    }
//
//
//    @Override  //localDate 객체를 문자열로
//    public String print(LocalDate object, Locale locale) {
//        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
//    }
        @Override
        public LocalDate parse(String text, Locale locale) {
            return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        @Override
        public String print(LocalDate object, Locale locale) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
        }
}

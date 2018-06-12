package pl.devwannabe.viewResolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import pl.devwannabe.View.CsvView;

import java.util.Locale;

public class CsvViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {

        return new CsvView();
    }
}

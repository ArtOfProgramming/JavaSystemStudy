package com.daiwei.viewresolver;

import java.util.Locale;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

//@Component
//@Order(1)
public class MyViewResolver implements ViewResolver, Ordered {
    private int order;

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (viewName.startsWith("msb:")) {
            System.out.println(viewName);
            return new MyVier();
        }

        return null;
    }

    @Override
    public int getOrder() {
        return order;
    }
}

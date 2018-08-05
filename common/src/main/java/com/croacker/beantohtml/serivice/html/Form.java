package com.croacker.beantohtml.serivice.html;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Form {

    public static final String TEMPLATE =
            "<form method='post'>" +
                    "<div class='bean-form'>" +
                    "{0}" +
                    "</div>" +
                    "<div><button type='submit'>Save</button></div>" +
                    "</form>";

    private List<FormField> fields = new ArrayList<>();

    public void addField(FormField field) {
        fields.add(field);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        fields.forEach(stringBuilder::append);
        return MessageFormat.format(TEMPLATE, stringBuilder);
    }
}

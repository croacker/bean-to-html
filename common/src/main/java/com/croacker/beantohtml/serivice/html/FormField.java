package com.croacker.beantohtml.serivice.html;

import java.text.MessageFormat;

public class FormField {

    public static final String TEMPLATE =
            "<div class='bean-form-field-label'>{0}:</div>" +
            "<div class='bean-form-field-value'>" +
            " <input type='text' autocomplete='off' name=''{0}'' value=''{1}''/>" +
            "</div>";

    private String label;

    private String value;

    public FormField(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return MessageFormat.format(TEMPLATE, getLabel(), getValue());
    }
}

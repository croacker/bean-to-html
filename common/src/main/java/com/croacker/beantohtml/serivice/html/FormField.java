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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MessageFormat.format(TEMPLATE, getLabel(), getValue());
    }
}

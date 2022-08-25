package Enums;

import java.lang.ref.PhantomReference;

public enum MessageSubject {
    CUSTOMER_SERVICE("Customer service"), WEBMASTER("Webmaster");

    private String value;

    MessageSubject(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

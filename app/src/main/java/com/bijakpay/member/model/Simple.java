package com.bijakpay.member.model;

import java.io.Serializable;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public class Simple implements Serializable {

    private String key;
    private String value;

    public Simple() {}

    public Simple(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {

        return key;
    }

    public String getValue() {
        return value;
    }
}

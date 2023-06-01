package com.example.services.reqres.entities.example;

import com.example.services.reqres.entities.Support;
import lombok.Getter;

@Getter
public enum SupportExample {

    SUPPORT_EXAMPLE {
        public Support getSupport() {
            return new Support(
                    "https://reqres.in/#support-heading",
                    "To keep ReqRes free, contributions towards server costs are appreciated!"
            );
        }
    };

    public abstract Support getSupport();
}

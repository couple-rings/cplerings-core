package com.cplerings.core.application.shared.errorcode;

public interface ErrorCode {

    enum Type {

        VALIDATION, BUSINESS, SYSTEM
    }

    class System implements ErrorCode {

        private static final System INSTANCE = new System();

        private System() {
        }

        @Override
        public String getCode() {
            return "000";
        }

        @Override
        public String getDescriptionLocale() {
            return "system.error";
        }

        @Override
        public Type getType() {
            return Type.SYSTEM;
        }
    }

    ErrorCode SYSTEM_ERROR = System.INSTANCE;

    String getCode();

    String getDescriptionLocale();

    Type getType();
}

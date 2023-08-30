package com.paksi.tokopediaassignment.common;

public class Constants {
    public class PaymentType {
        private PaymentType () {}
        
        public static final String BANK_TRANSFER = "BANK TRANSFER";
    }

    public class Message {
        private Message () {}

        public static final String PAYMENT_DELETED = "PAYMENT DELETED";
    }

    public class Filter {
        private Filter () {}

        public static final String CUSTOMER_ID = "customerId";
        public static final String PAYMENT_TYPE_NAME = "paymentTypeName";
        public static final String AMOUNT_FROM = "amountFrom";
        public static final String AMOUNT_TO = "amountTo";
        public static final String PAGE_NUMBER = "page";
        public static final String PAGE_SIZE = "size";
    }
}

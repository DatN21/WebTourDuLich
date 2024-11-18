package com.dulich.toudulich.enums;

public enum Status {
    PAUSED("Tạm dừng"),   // Tạm dừng
    ACTIVE("Đang hoạt động");

    // Phương thức kiểm tra xem giá trị status có hợp lệ không
    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static Status fromString(String value) {
        for (Status status : Status.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid tour type: " + value);
    }
}

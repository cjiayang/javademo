package net.jyou.javase.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotifyChannel implements IDictionary {
    WORK_WECHAT(1,"企业微信"),
    FEI_SHU(2, "飞书");
    private final int key;
    private final String label;
}

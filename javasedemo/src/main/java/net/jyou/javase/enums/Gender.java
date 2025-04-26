package net.jyou.javase.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender implements IDictionary {
    MAIL(1,"男","#409EFF"),
    FEMAIL(2,"女","#67C23A");
    private final int key;
    private final String label;
    private final String color;
}

package net.jyou.gson.demo.objects;

import java.util.Objects;

/**
 * @author Joey Chen
 * @created 2023/7/30 9:41
 */
public class BagOfPrimitives {
    private static final long DEFAULT_VALUE = 0;
    private final long longValue;
    private final int intValue;
    private final boolean booleanValue;
    private final String stringValue;

    public BagOfPrimitives() {
        this(DEFAULT_VALUE, 2, false, "abc");
    }

    public BagOfPrimitives(long longValue, int intValue, boolean booleanValue, String stringValue) {
        this.longValue = longValue;
        this.intValue = intValue;
        this.booleanValue = booleanValue;
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getExpectedJson() {
        return "{"
                + "\"longValue\":" + longValue + ","
                + "\"intValue\":" + intValue + ","
                + "\"booleanValue\":" + booleanValue + ","
                + "\"stringValue\":\"" + stringValue + "\""
                + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (booleanValue ? 1231 : 1237);
        result = prime * result + intValue;
        result = prime * result + (int) (longValue ^ (longValue >>> 32));
        result = prime * result + ((stringValue == null) ? 0 : stringValue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BagOfPrimitives)) {
            return false;
        }
        BagOfPrimitives that = (BagOfPrimitives) o;
        return longValue == that.longValue
                && intValue == that.intValue
                && booleanValue == that.booleanValue
                && Objects.equals(stringValue, that.stringValue);
    }

    @Override
    public String toString() {
        return String.format("(longValue=%d,intValue=%d,booleanValue=%b,stringValue=%s)",
                longValue, intValue, booleanValue, stringValue);
    }
}

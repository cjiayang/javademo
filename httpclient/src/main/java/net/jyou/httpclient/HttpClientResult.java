package net.jyou.httpclient;

import lombok.Data;

/**
 * @author Joey Chen
 * @created 2023/3/20 18:14
 */
@Data
public class HttpClientResult {
    private int status;
    private String content;
    public HttpClientResult(int status) {
        this.status = status;
    }

    public HttpClientResult(int status, String content) {
        this.status = status;
        this.content = content;
    }
}

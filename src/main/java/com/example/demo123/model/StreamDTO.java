package com.example.demo123.model;

public class StreamDTO {

    private String stream;
    private BtcEthDTO data;

    public StreamDTO() {
    }

    public String getStream() {
        return stream;
    }

    public StreamDTO setStream(String stream) {
        this.stream = stream;
        return this;
    }

    public BtcEthDTO getData() {
        return data;
    }

    public StreamDTO setData(BtcEthDTO data) {
        this.data = data;
        return this;
    }
}

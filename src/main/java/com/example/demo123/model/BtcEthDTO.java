package com.example.demo123.model;

import java.util.ArrayList;
import java.util.List;

public class BtcEthDTO {

    private String s; // symbol [BTC,ETH]
    private List<String[]> b = new ArrayList<>(); // bids
    private List<String[]> a = new ArrayList<>(); // asks
    private long E; // event time

    public BtcEthDTO() {
    }

    public String getS() {
        return s;
    }

    public BtcEthDTO setS(String s) {
        this.s = s;
        return this;
    }

    public List<String[]> getB() {
        return b;
    }

    public BtcEthDTO setB(List<String[]> b) {
        this.b = b;
        return this;
    }

    public List<String[]> getA() {
        return a;
    }

    public BtcEthDTO setA(List<String[]> a) {
        this.a = a;
        return this;
    }

    public long getE() {
        return E;
    }

    public BtcEthDTO setE(long e) {
        E = e;
        return this;
    }

    public String toStringAsks() {
        return "[" + this.getB().toString() + "]";
    }

    public String toStringBids() {
        return "[" + this.getA().toString() + "]";
    }
}





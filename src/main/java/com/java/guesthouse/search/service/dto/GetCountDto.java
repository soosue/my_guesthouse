package com.java.guesthouse.search.service.dto;

public class GetCountDto {
    private int count;
    private int max;
    private int min;

    public GetCountDto() {
    }

    public GetCountDto(int count, int max, int min) {
        this.count = count;
        this.max = max;
        this.min = min;
    }

    public int getCount() {
        return count;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String toString() {
        return "GetCountDto [count=" + count + ", max=" + max + ", min=" + min + "]";
    }

}

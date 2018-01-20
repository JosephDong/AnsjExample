package com.joseph.ansj.train;

import java.io.Serializable;

public class TrainInfo implements Serializable {
    private static final long serialVersionUID = 7213683354922637907L;
    private String orderSn;
    private String name;
    private String date;
    private String trainNumber;
    private String wagonBox;
    private String seatNumber;
    private String station;
    private String time;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getWagonBox() {
        return wagonBox;
    }

    public void setWagonBox(String wagonBox) {
        this.wagonBox = wagonBox;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TrainInfo{" +
                "orderSn='" + orderSn + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", wagonBox='" + wagonBox + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", station='" + station + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

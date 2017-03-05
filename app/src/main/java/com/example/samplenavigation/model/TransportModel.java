package com.example.samplenavigation.model;

/**
 * Created by RASHMI on 3/4/2017.
 */

public class TransportModel {

    int id;
    String name;
    FromCentralModel fromcentral;
    LocationModel location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FromCentralModel getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(FromCentralModel fromcentral) {
        this.fromcentral = fromcentral;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public class FromCentralModel {
        String car;
        String train;

        public String getCar() {
            return car;
        }

        public void setCar(String car) {
            this.car = car;
        }

        public String getTrain() {
            return train;
        }

        public void setTrain(String train) {
            this.train = train;
        }

    }

    public class LocationModel {
        String latitude;
        String longitude;

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

    }
}

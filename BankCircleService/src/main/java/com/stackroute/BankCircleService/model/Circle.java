package com.stackroute.BankCircleService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Circle {
    @Id
    private String circleId;
    private String circleName;
    private String circleDescription;
    private String createdBy;

    public Circle(String userId, String circleId, String circleName, String circleDescription, String createdBy) {
        this.circleId = circleId;
        this.circleName = circleName;
        this.circleDescription = circleDescription;
        this.createdBy = createdBy;
    }

    public Circle() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleDescription() {
        return circleDescription;
    }

    public void setCircleDescription(String circleDescription) {
        this.circleDescription = circleDescription;
    }

    @Override
    public String toString() {
        return "Circle{" +
                ", circleId='" + circleId + '\'' +
                ", circleName='" + circleName + '\'' +
                ", circleDescription='" + circleDescription + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}

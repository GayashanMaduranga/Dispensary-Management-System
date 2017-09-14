package com.EntityClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Embeddable;
import java.sql.Date;

/**
 * Created by gayashan on 9/13/2017.
 */
@Embeddable
public class Leave {

    Date date;
    StringProperty reason;

    public Leave() {
        this.reason = new SimpleStringProperty();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason.get();
    }

    public StringProperty reasonProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }
}

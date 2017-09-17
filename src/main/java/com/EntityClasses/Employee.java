package com.EntityClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import org.hibernate.annotations.Type;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gayashan on 9/10/2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
public abstract class Employee {

    private IntegerProperty employeeid;
    private StringProperty name;
    private StringProperty gender;
    private Date dateOfBirth;
    private StringProperty email;
    private StringProperty contactNumber;
    private StringProperty qualifications;
    private Address address;
    private StringProperty nic;
    private BufferedImage image;
    private byte[] imageByte;
    private List<PreviousEmployment> previousEmploymentList;
    private List<Education> educationList;





    public Employee() {
        this.employeeid = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.contactNumber = new SimpleStringProperty();
        this.qualifications = new SimpleStringProperty();
        this.previousEmploymentList = new ArrayList<>();
        this.educationList = new ArrayList<>();
        this.nic = new SimpleStringProperty();

    }



    @Id
    public int getEmployeeid() {
        return employeeid.get();
    }

    public IntegerProperty employeeidProperty() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid.set(employeeid);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    @Lob
    public String getQualifications() {
        return qualifications.get();
    }

    public StringProperty qualificationsProperty() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications.set(qualifications);
    }

    @ElementCollection
    public List<PreviousEmployment> getPreviousEmploymentList() {
        return previousEmploymentList;
    }

    public void setPreviousEmploymentList(List<PreviousEmployment> previousEmploymentList) {
        this.previousEmploymentList = previousEmploymentList;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Transient
    public BufferedImage getImage() {

        image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(imageByte));
            System.out.println("OK");
        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public void setImage(BufferedImage image) {
        WritableRaster raster = image.getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
        imageByte = data.getData();
        this.image = image;
    }

    @Lob
    @Column(name="IMAGE", columnDefinition="longblob")
    private byte[] getImageByte() {
        return imageByte;
    }

    private void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }


    @ElementCollection
    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public String getNic() {
        return nic.get();
    }

    public StringProperty nicProperty() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic.set(nic);
    }
}


package com.example.om.mutualtransferdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PrimaryResult implements Serializable{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("current_location")
    @Expose
    private String currentLocation;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("school_type")
    @Expose
    private String schoolType;
    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("category")
    @Expose
    private String category;


    @SerializedName("department_name")
    @Expose
    private String department_name;

    @SerializedName("grade_pay")
    @Expose
    private String grade_pay;


    @SerializedName("basic_pay")
    @Expose
    private String basic_pay;

    @SerializedName("appointment_date")
    @Expose
    private String appointment_date;


    @SerializedName("choice_param1")
    @Expose
    private String choice_param1;

    @SerializedName("choice_param2")
    @Expose
    private String choice_param2;

    @SerializedName("school_name")
    @Expose
    private String school_name;

    @SerializedName("scheme")
    @Expose
    private String scheme;

    @SerializedName("railway_zone_name")
    @Expose
    private String railway_zone_name;

    @SerializedName("division")
    @Expose
    private String division;

    @SerializedName("category_name")
    @Expose
    private String category_name;

    @SerializedName("post_category")
    @Expose
    private  String post_category;

    @SerializedName("commission")
    @Expose
    private String commission;

    @SerializedName("traned")
    @Expose
    private String traned;

    @SerializedName("qualification")
    @Expose
    private  String qualification;

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getPost_category() {
        return post_category;
    }

    public void setPost_category(String post_category) {
        this.post_category = post_category;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getTraned() {
        return traned;
    }

    public void setTraned(String traned) {
        this.traned = traned;
    }

    public String getRailway_zone_name() {
        return railway_zone_name;
    }

    public void setRailway_zone_name(String railway_zone_name) {
        this.railway_zone_name = railway_zone_name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getChoice_param1() {
        return choice_param1;
    }

    public void setChoice_param1(String choice_param1) {
        this.choice_param1 = choice_param1;
    }

    public String getChoice_param2() {
        return choice_param2;
    }

    public void setChoice_param2(String choice_param2) {
        this.choice_param2 = choice_param2;
    }

    public String getBasic_pay() {
        return basic_pay;
    }

    public void setBasic_pay(String basic_pay) {
        this.basic_pay = basic_pay;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getGrade_pay() {
        return grade_pay;
    }

    public void setGrade_pay(String grade_pay) {
        this.grade_pay = grade_pay;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * 
     * @param mobileNumber
     *     The mobile_number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * 
     * @return
     *     The currentLocation
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * 
     * @param currentLocation
     *     The current_location
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * 
     * @return
     *     The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 
     * @param department
     *     The department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 
     * @return
     *     The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 
     * @param avatar
     *     The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * @return
     *     The designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * 
     * @param designation
     *     The designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 
     * @param subject
     *     The subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 
     * @return
     *     The schoolType
     */
    public String getSchoolType() {
        return schoolType;
    }

    /**
     * 
     * @param schoolType
     *     The school_type
     */
    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

}

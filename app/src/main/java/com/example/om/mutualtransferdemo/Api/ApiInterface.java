package com.example.om.mutualtransferdemo.Api;

import com.example.om.mutualtransferdemo.CentralDepartment;
import com.example.om.mutualtransferdemo.DepartmentGradPay;
import com.example.om.mutualtransferdemo.DesignationKaranatakaState;
import com.example.om.mutualtransferdemo.DistrictKarnatakaPrimarySchool;
import com.example.om.mutualtransferdemo.DistrictOtherCentral;
import com.example.om.mutualtransferdemo.HighSchoolSubject;
import com.example.om.mutualtransferdemo.HighSchoolWestBangal;
import com.example.om.mutualtransferdemo.Model;
import com.example.om.mutualtransferdemo.PrimaryResult;
import com.example.om.mutualtransferdemo.PrimaryWestBangal;
import com.example.om.mutualtransferdemo.RailwayDesignation;
import com.example.om.mutualtransferdemo.RailwayDivision;
import com.example.om.mutualtransferdemo.RailwayZone;
import com.example.om.mutualtransferdemo.SchoolOfficeSelection;
import com.example.om.mutualtransferdemo.StateGovernDepartment;
import com.example.om.mutualtransferdemo.StateName;
import com.example.om.mutualtransferdemo.SubjectKaranatakaState;
import com.example.om.mutualtransferdemo.WestBangalState;

import java.util.List;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by om on 12/17/2016.
 */
public interface ApiInterface {

//    public interface mutualTransfer
//    {
//        @GET("mutualtrasnfer/states")
//        retrofit2.Call<List<ResponseBody>> list(@Path("state") String state);
//
//    }

    @GET("states")
    Call<List<StateName>> getStateName();

    @GET("central-departments")
    Call<List<CentralDepartment>> getDepartmentName();

    @GET("state-departments")
    Call<List<StateGovernDepartment>> getGDepartmentName(@Query("state_id") String id);

    @GET("railway/department-gradpay")
    Call<DepartmentGradPay> getDepartmentGradPay();

    @GET("railway/railway-zones")
    Call<List<RailwayZone>> getRailwayZone();

    @GET("railway/railway-divisions/{zone_id}")
    Call<List<RailwayDivision>> getRailwayDivision(@Path("zone_id") String id);

    @GET("designation/{designation_id}")
    Call<List<RailwayDesignation>> getRailwayDesignation(@Path("designation_id") String id);

    @GET("cities/{state_name}")
    Call<List<DistrictOtherCentral>> getDistrict(@Path("state_name") String state);

    @GET("stateGovernmentSchool/school-selections")
    Call<List<SchoolOfficeSelection>> getSchoolOfficeSelection();

    @GET("stateGovernmentSchool/school-districts")
    Call<List<DistrictKarnatakaPrimarySchool>> getDistrictKaranataka();

    @GET("stateGovernmentSchool/school-designations/{school_id}")
    Call<List<DesignationKaranatakaState>> getDesignationKaranataka(@Path("school_id") String id);

    @GET("stateGovernmentSchool/school-subjects/{designation_id}")
    Call<List<SubjectKaranatakaState>> getSubjectKaranataka(@Path("designation_id") String id);

    @GET("stateGovernmentSchool/wb-details")
    Call<HighSchoolWestBangal> getHighSchoolWestBangal();

    @GET("stateGovernmentSchool/wb-subject/{category_id}")
    Call<List<HighSchoolSubject>> getHighSchoolSubject(@Path("category_id") String id);

    @GET("stateGovernmentSchool/wbs-basic-data")
    Call<PrimaryWestBangal> getPrimaryWestBangal();

    @GET("cities/{state_name}")
    Call<List<WestBangalState>> getWestBangalDistrict(@Path("state_name") String id);

    @FormUrlEncoded
    @POST("user/register-user-temp")
    Call<ResponseBody> postIndianRailway(@Field("name") String name, @Field("email") String email, @Field("mobile_number") String mobileNumber
                                         , @Field("current_location") String currentLocation,
                                         @Field("department") int department,
                                         @Field("avatar") String avatar
                                         , @Field("designation") String designation, @Field("choice_param1") String choiceParam1, @Field("choice_param2") String choice_param2
                                         , @Field("railway_zone_id") String railwayZoneId, @Field("railway_division_id") String railwayDivisionId
                                         , @Field("category_id") int categoryId, @Field("grade_pay_id") int gradePayId,
                                         @Field("department_id") String departmentId
                                         , @Field("basic_pay") String basicPay,
                                         @Field("appointment_date") String appointmentDate
                                         , @Field("central_state") String centralState);


    @FormUrlEncoded
    @POST("user/is-user-registered")
    Call<ResponseBody> postCheckRegistration(@Field("email") String email);

    @FormUrlEncoded
    @POST("user/register-user-temp")
    Call<ResponseBody> postKaranataka(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("mobile_number") String mobileNumber,
                                      @Field("current_location") String currentLocation,
                                      @Field("department") int department,
                                      @Field("avatar") String avatar,
                                      @Field("designation") String designation,
                                      @Field("choice_param1") int choiceparam1,
                                      @Field("choice_param2") String choiceparam2,
                                      @Field("school_selection") int schoolselection,
                                      @Field("subject") String subject,
                                      @Field("scheme") int scheme,
                                      @Field("district") int district,
                                      @Field("central_state") int centralstate);

    @FormUrlEncoded
    @POST("user/register-user-temp")
    Call<ResponseBody> postWestBangal(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("mobile_number") String mobileNumber,
                                      @Field("current_location") String currentLocation,
                                      @Field("department") int department,
                                      @Field("avatar") String avatar,
                                      @Field("designation") String designation,
                                      @Field("choice_param1") int choiceparam1,
                                      @Field("choice_param2") String choiceparam2,
                                      @Field("category_id") String categoryid,
                                      @Field("designation_id") String designationid,
                                      @Field("qualification_id") String qualificationid,
                                      @Field("traned_id") String tranedid,
                                      @Field("current_location_id") String currentlocationid,
                                      @Field("school_type") String schooltype,
                                      @Field("central_state") int centralstate);

    @FormUrlEncoded
    @POST("user/register-user-temp")
    Call<ResponseBody> postWestBengalHighSchool(@Field("name") String name,
                                                @Field("email") String email,
                                                @Field("mobile_number") String mobileNumber,
                                                @Field("current_location") String currentLocation,
                                                @Field("department") int department,
                                                @Field("avatar") String avatar,
                                                @Field("designation") String designation,
                                                @Field("choice_param1") int choiceparam1,
                                                @Field("choice_param2") String choiceparam2,
                                                @Field("category_id") String categoryid,
                                                @Field("subject_id") String subjectid,
                                                @Field("post_category_id") String postcategoryid,
                                                @Field("commission_id") String commissionid,
                                                @Field("school_type_id") String schooltypeid,
                                                @Field("traned_id") String tranedid,
                                                @Field("current_location_id") String currentlocationid,
                                                @Field("school_type") String schooltype,
                                                @Field("central_state") int centralstate);

    @FormUrlEncoded
    @POST("user/register-user-temp")
    Call<ResponseBody> postOtherState(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("mobile_number") String mobileNumber,
                                      @Field("current_location") String currentLocation,
                                      @Field("department") String department,
                                      @Field("avatar") String avatar,
                                      @Field("designation") String designation,
                                      @Field("choice_param1") int choiceparam1,
                                      @Field("choice_param2") String choiceparam2,
                                      @Field("central_state") int centralstate);

    @FormUrlEncoded
    @POST("user/register-user-temp")
    Call<ResponseBody> postOtherCentral(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("mobile_number") String mobileNumber,
                                      @Field("current_location") String currentLocation,
                                      @Field("department") String department,
                                      @Field("avatar") String avatar,
                                      @Field("designation") String designation,
                                      @Field("choice_param1") int choiceparam1,
                                      @Field("choice_param2") String choiceparam2,
                                      @Field("central_state") int centralstate);

    @GET("search/matching-temp")
    Call<Model> getmatching(@Query("department_id") String departmentId, @Query("self_id") String userId);

}

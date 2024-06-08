package com.reza.jobportal.domain;

public interface IRecruiterJobs {

    Long getTotalCandidates();
    Integer getJob_Post_Id();
    String getJob_Title();
    Integer getLocationId();
    String getCity();
    String getState();
    String getCountry();
    Integer getCompanyId();
    String getName();
}

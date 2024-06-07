package com.reza.jobportal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruiter_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RecruiterProfile {
    @Id
    private Integer userAccountId;
    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private Users userId;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String company;
    @Column(nullable = true, length = 64)
    private String profilePhoto;

    public RecruiterProfile(Users userId) {
        this.userId = userId;
    }

    @Transient
    public String getPhotosImagePath() {
        return profilePhoto == null ? null : "/photos/recruiter/" + userAccountId + "/" + profilePhoto;
    }
}

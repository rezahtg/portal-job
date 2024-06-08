package com.reza.jobportal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_post_activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class JobPostActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobPostId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postedById", referencedColumnName = "userId")
    private Users postedById;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobLocationId", referencedColumnName = "id")
    private JobLocation jobLocationId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobCompanyId", referencedColumnName = "id")
    private JobCompany jobCompanyId;
    @Transient
    private Boolean isActive;
    @Transient
    private Boolean isSaved;
    @Length(max = 10000)
    private String descriptionOfJob;
    private String jobType;
    private String salary;
    private String remote;
    @CreationTimestamp
    private LocalDateTime postedDate;
    private String jobTitle;
}

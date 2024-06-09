package com.reza.jobportal.controller;

import com.reza.jobportal.domain.JobSeekerProfile;
import com.reza.jobportal.domain.Skills;
import com.reza.jobportal.domain.Users;
import com.reza.jobportal.repository.UsersRepository;
import com.reza.jobportal.services.JobSeekerProfileService;
import com.reza.jobportal.util.FileUploadUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/job-seeker-profile")
public class JobSeekerProfileController {

    private final JobSeekerProfileService jobSeekerProfileService;
    private final UsersRepository usersRepository;

    @GetMapping("/")
    public String jobSeekerProfile(Model model) {
        JobSeekerProfile jobSeekerProfile = new JobSeekerProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Skills> skills = new ArrayList<>();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users users = usersRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            Optional<JobSeekerProfile> jobSeeker = jobSeekerProfileService.getOne(users.getUserId());
            if (jobSeeker.isPresent()) {
                jobSeekerProfile = jobSeeker.get();
                if (jobSeekerProfile.getSkills().isEmpty()) {
                    skills.add(new Skills());
                    jobSeekerProfile.setSkills(skills);
                }
            }

            model.addAttribute("skills", skills);
            model.addAttribute("profile", jobSeekerProfile);
        }

        return "job-seeker-profile";
    }

    @PostMapping("/addNew")
    public String addNew(
            JobSeekerProfile jobSeekerProfile,
            @RequestParam("image") MultipartFile image,
            @RequestParam("pdf") MultipartFile pdf,
            Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users users = usersRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            jobSeekerProfile.setUserId(users);
            jobSeekerProfile.setUserAccountId(users.getUserId());
        }
        List<Skills> skillsList = new ArrayList<>();
        model.addAttribute("profile", jobSeekerProfile);
        model.addAttribute("skills", skillsList);

        for (Skills skills : jobSeekerProfile.getSkills()) {
            skills.setJobSeekerProfile(jobSeekerProfile);
        }
        String imageName = "";
        String pdfName = "";

        if (!Objects.equals(image.getOriginalFilename(), "")) {
            imageName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            jobSeekerProfile.setProfilePhoto(imageName);
        }

        if (!Objects.equals(pdf.getOriginalFilename(), "")) {
            pdfName = StringUtils.cleanPath(Objects.requireNonNull(pdf.getOriginalFilename()));
            jobSeekerProfile.setResume(pdfName);
        }

        JobSeekerProfile seekerProfile = jobSeekerProfileService.addNew(jobSeekerProfile);

        try {
            String uploadDir = "src/main/resources/static/photos/candidate/" + (seekerProfile.getUserAccountId());
            if (!Objects.equals(image.getOriginalFilename(), "")) {
                FileUploadUtils.saveFile(uploadDir, imageName, image);
            }
            if (!Objects.equals(pdf.getOriginalFilename(), "")) {
                FileUploadUtils.saveFile(uploadDir, pdfName, pdf);
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

        return "redirect:/dashboard/";
    }

}

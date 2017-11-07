package com.project.License.services;
import com.project.License.models.License;
import com.project.License.repositories.LicenseRepository;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    private LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository){
        this.licenseRepository = licenseRepository;
    }

    public void addLicense(License license) {
        licenseRepository.save(license);
    }
}
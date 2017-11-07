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

    public License addLicense(License license) {
        System.out.println("Hello");
        Long personId = license.getPerson().getId();
        String licenseNum = String.format("%06d", personId);
        license.setNumber(licenseNum);
        License lic = licenseRepository.save(license);
        return lic;
    }
}
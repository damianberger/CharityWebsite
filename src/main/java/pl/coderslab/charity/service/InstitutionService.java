package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Institution;

import java.util.List;

public interface InstitutionService {
    List<Institution> findAll();
    void save(Institution institution);
    void edit(Institution institution);
    Institution findById(Long id);
    void deleteInstitution(Long id);
}

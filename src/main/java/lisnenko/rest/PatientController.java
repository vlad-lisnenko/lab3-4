package lisnenko.rest;

import java.util.List;

import lisnenko.entity.Patient;
import lisnenko.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/patient")
public class PatientController {

  private final PatientService patientService;

  @GetMapping
  public List<Patient> getAllPatients() {
    return patientService.getAllPatients();
  }

  @GetMapping("/{lastName}")
  public Patient getByLastName(@PathVariable String lastName) {
    return patientService.getPatientBySurname(lastName);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Patient patient) {
    patientService.createPatient(patient);
    return ResponseEntity.created(null).build();
  }
}

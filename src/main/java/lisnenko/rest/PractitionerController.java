package lisnenko.rest;

import java.util.List;

import lisnenko.entity.Practitioner;
import lisnenko.service.PractitionerService;
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
@RequestMapping("v1/practitioner")
public class PractitionerController {

  private final PractitionerService practitionerService;

  @GetMapping
  public List<Practitioner> getAllPractitioners() {
    return practitionerService.findAllPractitioners();
  }

  @GetMapping("/{lastName}")
  public Practitioner getByLastName(@PathVariable String lastName) {
    return practitionerService.getByLastName(lastName);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Practitioner practitioner) {
    practitionerService.createPractitioner(practitioner);
    return ResponseEntity.created(null).build();
  }
}

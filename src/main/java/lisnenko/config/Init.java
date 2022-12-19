package lisnenko.config;

import java.time.LocalDateTime;
import java.util.List;

import lisnenko.entity.Patient;
import lisnenko.entity.Practitioner;
import lisnenko.entity.Slot;
import lisnenko.repository.PatientRepository;
import lisnenko.repository.PractitionerRepository;
import lisnenko.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init implements ApplicationListener<ApplicationReadyEvent> {

  private final SlotRepository slotRepository;
  private final PatientRepository patientRepository;
  private final PractitionerRepository practitionerRepository;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    log.info("Inserting practitioners");
    var practitioners = getPractitionersList();
    practitionerRepository.saveAll(practitioners);
    log.info("Practitioners were successfully saved. Size: " + practitioners.size());

    log.info("Inserting patients");
    var patients = getPatientsList();
    patientRepository.saveAll(patients);
    log.info("Patients were successfully saved. Size: " + patients.size());

    log.info("Inserting slots");
    var slots = getSlots();
    slotRepository.saveAll(slots);
    log.info("Slots were successfully saved. Size: " + slots.size());
  }

  private List<Practitioner> getPractitionersList() {
    var firstPractitioner = new Practitioner();
    firstPractitioner.setFirstName("Mark");
    firstPractitioner.setLastName("Clarkins");
    var secondPractitioner = new Practitioner();
    secondPractitioner.setFirstName("Gigi");
    secondPractitioner.setLastName("Elens");

    return List.of(firstPractitioner, secondPractitioner);
  }

  private List<Patient> getPatientsList() {
    var firstPatient = new Patient();
    firstPatient.setFirstName("Sina");
    firstPatient.setLastName("Sepster");
    var secondPatient = new Patient();
    secondPatient.setFirstName("Portel");
    secondPatient.setLastName("Gau");

    return List.of(firstPatient, secondPatient);
  }

  private List<Slot> getSlots() {
    return List.of(
        new Slot(1L, LocalDateTime.of(2022, 12, 8, 14, 0, 0), true),
        new Slot(1L, LocalDateTime.of(2022, 12, 8, 13, 0, 0), false),
        new Slot(2L, LocalDateTime.of(2022, 12, 8, 10, 0, 0), true),
        new Slot(2L, LocalDateTime.of(2022, 12, 8, 9, 0, 0), false)
        );
  }
}

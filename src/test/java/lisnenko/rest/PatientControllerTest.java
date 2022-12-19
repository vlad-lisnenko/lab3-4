package lisnenko.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import lisnenko.entity.Patient;
import lisnenko.utils.AbstractApiTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

public class PatientControllerTest extends AbstractApiTest {

  private static final String PATH = "/v1/patient";

  @Autowired
  private ObjectMapper mapper;

  @Test
  @SneakyThrows
  void shouldGetAllPractitioners() {
    // when
    MvcResult result = performRequest(PATH);

    List<Patient> patients = mapper.readValue(result.getResponse().getContentAsString(),
        List.class);

    // then
    assertThat(patients)
        .isNotEmpty()
        .hasSize(3);
  }

  @Test
  @SneakyThrows
  void shouldGetPractitionerByLastName() {
    // when
    MvcResult result = performRequest(PATH + "/Sepster");

    Patient patient = mapper.readValue(result.getResponse().getContentAsString(),
        Patient.class);

    // then
    assertThat(patient.getLastName()).isEqualTo("Sepster");
  }

  @Test
  @SneakyThrows
  void shouldCreateNewPractitioner() {
    // when
    MvcResult result = performPostRequest(PATH, new Patient("Clark", "Token"));

    // then
    assertThat(result.getResponse().getStatus()).isEqualTo(201);
  }
}

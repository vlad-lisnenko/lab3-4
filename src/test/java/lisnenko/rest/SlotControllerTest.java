package lisnenko.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import lisnenko.entity.Slot;
import lisnenko.entity.dto.SlotDate;
import lisnenko.utils.AbstractApiTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

public class SlotControllerTest extends AbstractApiTest {

  private static final String PATH = "/v1/slot";

  @Test
  @SneakyThrows
  void shouldReturnAvailableSlotsForPractitioner() {
    // when
    var slotDate = new SlotDate(LocalDateTime.of(2022, 12, 8, 8, 0, 0),
        LocalDateTime.of(2022, 12, 8, 16, 0, 0));
    MvcResult result = performRequest(PATH + "/1", slotDate);

    List<Slot> availableSlotsForPractitioner = mapper.readValue(result.getResponse().getContentAsString(), List.class);

    // then
    assertThat(availableSlotsForPractitioner.size()).isEqualTo(1);
  }

  @Test
  @SneakyThrows
  void shouldReturnAllSlotsForPractitioner() {
    // when
    var slotDate = new SlotDate(LocalDateTime.of(2022, 12, 8, 8, 0, 0),
        LocalDateTime.of(2022, 12, 8, 16, 0, 0));
    MvcResult result = performRequest(PATH + "/all/1", slotDate);

    List<Slot> availableSlotsForPractitioner = mapper.readValue(result.getResponse().getContentAsString(), List.class);

    // then
    assertThat(availableSlotsForPractitioner.size()).isEqualTo(2);
  }

  @Test
  @SneakyThrows
  void shouldBookAvailableSlot() {
    // when
    MvcResult result = performPostRequest(PATH, 1);

    boolean isBooked = mapper.readValue(result.getResponse().getContentAsString(),
        Boolean.class);

    // then
    assertThat(isBooked).isTrue();
  }

  @Test
  @SneakyThrows
  void shouldNotBookBusySlot() {
    // when
    MvcResult result = performPostRequest(PATH, 2);

    boolean isBooked = mapper.readValue(result.getResponse().getContentAsString(),
        Boolean.class);

    // then
    assertThat(isBooked).isFalse();
  }

  @Test
  @SneakyThrows
  void shouldUnbookBusySlot() {
    // when
    MvcResult result = performDeleteRequest(PATH, 4);

    boolean isUnbooked = mapper.readValue(result.getResponse().getContentAsString(),
        Boolean.class);

    // then
    assertThat(isUnbooked).isTrue();
  }
}

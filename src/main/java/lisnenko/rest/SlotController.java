package lisnenko.rest;

import java.util.List;

import lisnenko.entity.Slot;
import lisnenko.entity.dto.SlotDate;
import lisnenko.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/slot")
public class SlotController {

  private final SlotService slotService;

  @GetMapping("/{practitionerId}")
  public List<Slot> getAvailableSlotsForPractitioner(@PathVariable Long practitionerId, @RequestBody SlotDate slotDate) {
    return slotService.listAvailableSlotsForPractitionerId(practitionerId, slotDate.from(), slotDate.to());
  }

  @GetMapping("all/{practitionerId}")
  public List<Slot> getAllSlotsForPractitioner(@PathVariable Long practitionerId, @RequestBody SlotDate slotDate) {
    return slotService.listAllSlotsForPractitionerId(practitionerId, slotDate.from(), slotDate.to());
  }

  @PostMapping
  public boolean bookSlot(@RequestBody Long slotId) {
    return slotService.bookSlot(slotId);
  }

  @DeleteMapping
  public boolean unbookSlot(@RequestBody Long slotId) {
    return slotService.unbookSlot(slotId);
  }
}

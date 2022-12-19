package lisnenko.service;

import java.time.LocalDateTime;
import java.util.List;

import lisnenko.entity.Slot;
import lisnenko.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlotService {

  private final SlotRepository repository;

  public List<Slot> listAvailableSlotsForPractitionerId(Long practitionerId, LocalDateTime fromDate, LocalDateTime toDate) {
    return repository.findAllByPractitionerIdAndTimeBetweenAndFreeIsTrue(practitionerId, fromDate, toDate);
  }

  public List<Slot> listAllSlotsForPractitionerId(Long practitionerId, LocalDateTime fromDate, LocalDateTime toDate) {
    return repository.findAllByPractitionerIdAndTimeBetween(practitionerId, fromDate, toDate);
  }

  public boolean bookSlot(Long id) {
    var slotOptional = repository.findById(id);
    if (slotOptional.isPresent() && slotOptional.get().isFree()) {
      var slot = slotOptional.get();
      slot.setFree(false);
      repository.save(slot);

      return true;
    } else {
      return false;
    }
  }

  public boolean unbookSlot(Long id) {
    var slotOptional = repository.findById(id);
    if (slotOptional.isPresent() && !slotOptional.get().isFree()) {
      var slot = slotOptional.get();
      slot.setFree(true);
      repository.save(slot);

      return true;
    } else {
      return false;
    }
  }
}

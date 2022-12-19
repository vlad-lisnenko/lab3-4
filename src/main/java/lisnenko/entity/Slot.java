package lisnenko.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "slot")
@NoArgsConstructor
public class Slot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long practitionerId;

  private LocalDateTime time;

  private boolean free;

  public Slot(Long practitionerId, LocalDateTime time, boolean isFree) {
    this.practitionerId = practitionerId;
    this.time = time;
    this.free = isFree;
  }
}

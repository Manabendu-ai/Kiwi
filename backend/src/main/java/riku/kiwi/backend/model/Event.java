package riku.kiwi.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import riku.kiwi.backend.domain.EventStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "mame", nullable = false)
    private String name;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;

    @Column(name = "venue")
    private String venue;

    @Column(name = "sales_start_date")
    private LocalDateTime salesStartDate;

    @Column(name = "sales_end_date")
    private LocalDateTime salesEndDate;

    @Column(name = "event_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @ManyToMany(mappedBy = "attendees", fetch = FetchType.LAZY)
    private List<User> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "staffingEvents", fetch = FetchType.LAZY)
    private List<User> staff = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

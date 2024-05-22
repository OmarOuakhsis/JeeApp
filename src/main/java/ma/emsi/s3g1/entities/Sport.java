package ma.emsi.s3g1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SPORT")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "active")
    private Boolean active;
}

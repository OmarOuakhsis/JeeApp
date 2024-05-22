package ma.emsi.s3g1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor // lombok

@Entity @Table(name = "EMSI_STUDENT")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "registration_N", length = 30, unique = true)
    @NotEmpty
    private String registrationNumber;
    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(min=5, max = 30)
    private String fullName;
    @Temporal(TemporalType.DATE)
    private Date birthday;

    private Boolean stillActive;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastConnction;
}

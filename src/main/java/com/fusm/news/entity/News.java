package com.fusm.news.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @Column(name =  "id_new", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newId;

    @NonNull
    @Column(name = "tittle", length = 200, nullable = false)
    private String tittle;

    @NonNull
    @Column(name = "content", length = 20000, nullable = false)
    private String content;

    @NonNull
    @Column(name = "cover", length = 200, nullable = false)
    private String cover;

    @NonNull
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @NonNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

}

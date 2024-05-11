package com.fusm.news.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "News_campus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsCampus {

    @Id
    @Column(name =  "id_news_campus", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newsCampusId;

    @NonNull
    @Column(name = "campus_id", nullable = false)
    private Integer campusId;

    @NonNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News newsId;

}

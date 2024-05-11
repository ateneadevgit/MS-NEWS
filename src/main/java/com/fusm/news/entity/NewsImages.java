package com.fusm.news.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "News_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsImages {

    @Id
    @Column(name =  "id_news_images", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newsImagesId;

    @NonNull
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @NonNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News newsId;

}

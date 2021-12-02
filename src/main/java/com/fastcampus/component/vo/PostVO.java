package com.fastcampus.component.vo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "hiddenBuilder")
@Entity
@Table(name = "POST")
public class PostVO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer postId;

    @Column(nullable = false)
    private Integer categoryId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date createdDate = new Date();

    public static PostVOBuilder builder(String title, String content){
        return hiddenBuilder()
                .title(title)
                .content(content);
    }



}

package com.guciowons.yummify.dish.infrastructure.out.jpa.entity;

import com.guciowons.yummify.common.file.infrastructure.out.jpa.JpaFile;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dish_image", schema = "dish")
public class JpaDishImage extends JpaFile {
}

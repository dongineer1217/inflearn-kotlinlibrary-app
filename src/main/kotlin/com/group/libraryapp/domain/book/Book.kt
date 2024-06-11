package com.group.libraryapp.domain.book

import javax.persistence.*

/**
 * 기본생성자가 필요
 * JPA entity로 등록하려면!
 */
@Entity
class Book(
    @Column(nullable = false)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }
}
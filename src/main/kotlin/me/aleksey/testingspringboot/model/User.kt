package me.aleksey.testingspringboot.model

import javax.persistence.*

@Entity
@Table(name = "users")
class User(

    @Column(name = "name")
    val name: String
) {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
}

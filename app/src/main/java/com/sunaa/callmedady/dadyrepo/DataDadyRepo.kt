package com.sunaa.callmedady.dadyrepo

class DataDadyRepo {
    private val professionList =
        listOf(
            "ENGINEER", "DOCTOR", "TEACHER", "HUSBAND", "WIFE", "PAINTER", "ACTOR", "PRODUCER",
            "INTERN", "DEVELOPER", "LOVER"
        )

    fun containsProfession(profession: String): Boolean {
        return professionList.contains(profession.uppercase().trim())
    }
}
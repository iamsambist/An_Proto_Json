package com.sunaa.callmedady.dadyrepo

import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test


class DataDadyRepoTest{
    private lateinit var dataDadyRepo: DataDadyRepo

    @Before
    fun setup() {
        dataDadyRepo = DataDadyRepo()
    }
    @Test
    fun `containsProfession should return true for a valid profession`() {
        val profession = "doctor"
        val result = dataDadyRepo.containsProfession(profession)
        assertTrue(result)
    }
}
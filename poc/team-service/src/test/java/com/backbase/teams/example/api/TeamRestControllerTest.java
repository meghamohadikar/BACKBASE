package com.backbase.teams.example.api;


import com.backbase.teams.controller.TeamRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TeamRestControllerTest
{


    TeamRestController teamRestController= new TeamRestController();


    @Test
    public void getTeams()
    {

        teamRestController.getTeams("Barcelona",null,null);


    }





}

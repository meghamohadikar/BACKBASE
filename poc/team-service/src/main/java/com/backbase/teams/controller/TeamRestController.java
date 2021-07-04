package com.backbase.teams.controller;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.team.rest.spec.v1.teams.TeamsApi;
import com.backbase.team.rest.spec.v1.teams.TeamsGetResponseBody;
import com.backbase.team.rest.spec.v1.teams.TeamsPostRequestBody;
import com.backbase.team.rest.spec.v1.teams.TheException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.SequenceWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TeamRestController implements TeamsApi {


    @Value("${value.from.file}")
    public  String valueFromFile;

    public static String file;



    @PostConstruct
    public void PostConstruct() throws IOException
    {

        this.file=valueFromFile;
    }

    private final Logger logger = LoggerFactory.getLogger(TeamRestController.class);
    @Override
    public List<TeamsGetResponseBody> getTeams(String city, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return parseTeamJson(city);
        } catch (Exception e) {
            logger.info("Error trying to get the Teams", e);
        }
        return null;
    }

    @Override
    public void postTeams(@RequestBody TeamsPostRequestBody teamsPostRequestBody, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws TheException {

        try {
            String json = executeRequest();

            File file = new File("../team_extras/teams.json");
            FileWriter fileWriter= new FileWriter(file, true);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(new File("../team_extras/teams.json"));
            String resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);

            //  Append a new Node teamsPostRequestBody to ArrayNode
            ObjectNode newTeamsObjectNode = mapper.createObjectNode();

            newTeamsObjectNode.put("name", teamsPostRequestBody.getName());
            newTeamsObjectNode.put("id", teamsPostRequestBody.getName());
            newTeamsObjectNode.put("homeCity", teamsPostRequestBody.getHomeCity());
            newTeamsObjectNode.put("stadium", teamsPostRequestBody.getStadium());

            JsonNode teamsNode = root.path("teams");
            ((ArrayNode) teamsNode).add(newTeamsObjectNode);

            String resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, root);
            httpServletResponse.setHeader("Location","");


        } catch (Exception e) {
            logger.info("Error trying to get the Teams", e);
        }


    }

    private List<TeamsGetResponseBody> parseTeamJson(String city) throws JSONException, IOException{
        String json = executeRequest();

        // @formatter:off
        List<TeamsGetResponseBody> response=  new ObjectMapper().readValue(new JSONObject(json).getJSONArray("teams").toString(), new TypeReference<List<TeamsGetResponseBody>>() {});

        // @formatter:on

        List<TeamsGetResponseBody> responseJSON =
                //Create a Stream from the response
                response.stream().
                        //filter the element to select only those which having same city as provided
                                filter(team -> city.equals(team.getHomeCity()))
                        //put those filtered elements into a new List.
                        .collect(Collectors.toCollection(() -> new ArrayList<TeamsGetResponseBody>()));

        return responseJSON;
    }

    private String executeRequest() throws IOException {


        logger.info("Looks like the server is down, getting the JSON from the file"+file);

        return new String(Files.readAllBytes(Paths.get(file)));

    }


}

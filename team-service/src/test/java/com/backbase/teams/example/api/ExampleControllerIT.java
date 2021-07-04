package com.backbase.teams.example.api;

import com.backbase.teams.Application;
import com.backbase.teams.example.generated.presentation.dummy.rest.spec.v1.example.DummyPostResponseBody;
import com.backbase.teams.example.generated.presentation.dummy.rest.spec.v1.example.DummyPostRequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;
import org.junit.runner.RunWith;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration test class for the ExampleController
 */
@SpringBootTest(classes = Application.class, properties = {
        "buildingblocks.security.xss.autoconfig=true"})
@ActiveProfiles("it")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ExampleControllerIT {
    static {
        System.setProperty("SIG_SECRET_KEY", "JWTSecretKeyDontUseInProduction!");
    }

    /**
     * Created using http://jwtbuilder.jamiekurtz.com/
     * <PRE>
     * {
     *   "Role" : [
     *     "Manager",
     *     "Project Administrator"
     *   ],
     *   "inuid" : "Jimmy",
     *   "aud" : "www.example.com",
     *   "sub" : "jrocket@example.com",
     *   "Email" : "jrocket@example.com",
     *   "iss" : "Online JWT Builder",
     *   "GivenName" : "Johnny",
     *   "exp" : 1516356196,
     *   "iat" : 1484820196,
     *   "Surname" : "Rocket"
     * }
     * </PRE>
     */
    public static final String TEST_JWT =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxk"
                    + "ZXIiLCJpYXQiOjE0ODQ4MjAxOTYsImV4cCI6MTUxNjM1NjE5NiwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJv"
                    + "Y2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2Nr"
                    + "ZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXSwiaW51aWQiOiJKaW1te"
                    + "SJ9.O9TE28ygrHmDjItYK6wRis6wELD5Wtpi6ekeYfR1WqM";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    public void testExampleCreate() throws Exception {
        DummyPostRequestBody requestContent = new DummyPostRequestBody()
                .withId("1")
                .withName("Foo");

        MockHttpServletRequestBuilder requestBuilder = post("/v1/example/create")
                .header("Authorization", TEST_JWT)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(requestContent));

        ResultActions result = mvc.perform(requestBuilder).andDo(print());

        // Then the request is successful
        result = result.andExpect(status().isCreated());

        // And a response is returned
        MvcResult response = result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        // Check response
        String responseBody = response.getResponse().getContentAsString();
        DummyPostResponseBody dummyPostResponseBody = mapper.readValue(responseBody, DummyPostResponseBody.class);

        assertThat("ID in response should match ID in request", dummyPostResponseBody.getId(), is(requestContent.getId()));
    }

}

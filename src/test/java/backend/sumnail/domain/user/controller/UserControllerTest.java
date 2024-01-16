package backend.sumnail.domain.user.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import backend.sumnail.domain.user.entity.User;
import backend.sumnail.global.config.jwt.PrincipalDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/user-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void 자신의_프로필을_조회_할_수_있다() throws Exception{

        //given
        User user = User.builder()
                .id(1L)
                .name("썸네일")
                .email("sed@yahoo.edu")
                .profileImage("https://guardian.co.uk/one")
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/profile")
                .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("썸네일"))
                .andExpect(jsonPath("$.email").value("sed@yahoo.edu"))
                .andExpect(jsonPath("$.profileImage").value("https://guardian.co.uk/one"));
    }

    @Test
    void 존재하지_않는_유저의_아이디로_요청할_경우_404_응답을_받는다() throws Exception {
        //given
        User user = User.builder()
                .id(102934563L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/profile")
                .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.error").value("해당 유저를 찾을 수 없습니다."));

    }

    @Test
    void 저장한_네일샵을_조회할_수_있다() throws Exception{
        //given
        User user = User.builder()
                .id(1L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/nail-shops")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nailShopId").value(1))
                .andExpect(jsonPath("$[0].nailShopName").value("썸네일네일샵"))
                .andExpect(jsonPath("$[0].location").value("서울시 중구"))
                .andExpect(jsonPath("$[0].titleImage").value("http://zoom.us?g=1"))
                .andExpect(jsonPath("$[0].hashtags", containsInAnyOrder("심플한", "화한")));
    }

    @Test
    void 저장한_네일샵이_없을_경우_빈_배열을_받환한다() throws Exception{
        //given
        User user = User.builder()
                .id(2L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/nail-shops")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }







}
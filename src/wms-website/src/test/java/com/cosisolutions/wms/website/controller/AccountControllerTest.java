package com.cosisolutions.wms.website.controller;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.repository.AccountRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-config.xml"})
public class AccountControllerTest {
  @Autowired
  private WebApplicationContext context;

  @Mock
  @Autowired
  private AccountRepository accountRepository;
  @Mock
  private View mockView;

  @Autowired
  @InjectMocks
  private AccountsController controller;

  private MockMvc mockMvc;

  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(springSecurity())
      .build();
  }

  @Test
  @WithAnonymousUser
  public void index_ShouldReturnToAccountLogin() throws Exception {
    mockMvc.perform(get("/accounts"))
      .andExpect(status().isOk())
      .andExpect(view().name("accounts/login"));
  }

  @Test
  public void edit_ShouldRedirectWhenWithoutUser() throws Exception {
    mockMvc.perform(get("/accounts/edit"))
      .andExpect(status().is3xxRedirection())
      .andExpect(redirectedUrlPattern("**/accounts/login"));
  }

  @Ignore
  @Test
  @WithMockUser(username = "user@user.com")
  public void edit_ShouldAllowEditWithUser() throws Exception {
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setId(1);
    accountEntity.setActive(true);
    accountEntity.setEmail("user@user.com");
    accountEntity.setFirstName("user");
    accountEntity.setLastName("userLastName");
    accountEntity.setRole("ROLE_USER");

    AccountRepository mock = mock(AccountRepository.class);
    when(mock.findUserByEmail("user@user.com")).thenReturn(accountEntity);

    mockMvc.perform(get("/accounts/edit"))
      .andExpect(status().isOk())
      .andExpect(view().name("accounts/edit"))
      .andExpect(model().attribute("model", hasProperty("id", is(1))))
      .andExpect(model().attribute("model", hasProperty("active", is(true))))
      .andExpect(model().attribute("model", hasProperty("email", is("user@user.com"))))
      .andExpect(model().attribute("model", hasProperty("firstName", is("user"))))
      .andExpect(model().attribute("model", hasProperty("lastName", is("userLastName"))))
      .andExpect(model().attribute("model", hasProperty("role", is("ROLE_USER"))));

    verify(accountRepository, times(1)).findUserByEmail("user");
    verifyNoMoreInteractions(accountRepository);
  }
}

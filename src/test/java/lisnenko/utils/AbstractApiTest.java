package lisnenko.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class AbstractApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  protected ObjectMapper mapper;

  protected MvcResult performRequest(String path) throws Exception {
    return mockMvc.perform(get(path))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(200))
        .andReturn();
  }

  protected MvcResult performRequest(String path, Object request) throws Exception {
    return mockMvc.perform(get(path)
            .content(mapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(200))
        .andReturn();
  }

  protected MvcResult performPostRequest(String path, Object request)
      throws Exception {
    return mockMvc.perform(post(path)
            .content(mapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }

  protected MvcResult performDeleteRequest(String path, Object request)
      throws Exception {
    return mockMvc.perform(delete(path)
            .content(mapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }
}

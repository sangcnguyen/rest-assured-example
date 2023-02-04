package post;

import core.APIClient;
import core.RequestWrapper;
import core.ResponseWrapper;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class PostAPITest {
    private SoftAssert softAsserts;
    private APIClient apiClient;

    private static final Map<String, String> HEADERS = new HashMap<>() {{
        put("Content-type", "application/json; charset=UTF-8");
    }};

    @BeforeClass
    public void init() {
        softAsserts = new SoftAssert();
        apiClient = new APIClient();
    }

    @Test
    public void testGetPost() {
        RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS).build();
        ResponseWrapper<Post> responseWrapper = apiClient.getEntity(Post.class, requestWrapper, "/posts/1");

        softAsserts.assertEquals(responseWrapper.getStatusCode(), 200, "Response status code should be 200");
        softAsserts.assertTrue(JsonUtils.isJsonSchemaValid("postSchema.json", responseWrapper.getResponseRaw().body().asString()));
        softAsserts.assertAll();
    }

    @Test
    public void testGetPosts() {
        RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS).build();
        ResponseWrapper<Post[]> responseWrapper = apiClient.getEntityArray(Post[].class, requestWrapper, "/posts");

        softAsserts.assertEquals(responseWrapper.getStatusCode(), 200, "Response status code should be 200");
        softAsserts.assertTrue(JsonUtils.isJsonSchemaValid("postGetSchema.json", responseWrapper.getResponseRaw().body().asString()));
        softAsserts.assertAll();
    }

    @Test
    public void testPostCreation() {
        Post post = Post.builder().userId(1).title("title").body("body").build();
        RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS).body(post).build();
        ResponseWrapper<Post> responseWrapper = apiClient.postEntity(Post.class, requestWrapper, "/posts");

        softAsserts.assertEquals(responseWrapper.getStatusCode(), 201, "Response status code should be 201");
        softAsserts.assertTrue(JsonUtils.isJsonSchemaValid("postCreateSchema.json", responseWrapper.getResponseRaw().body().asString()));
        softAsserts.assertAll();
    }

    @Test
    public void testPostUpdate() {
        Post post = Post.builder().userId(1).title("title").body("body").build();
        RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS).body(post).build();
        ResponseWrapper<Post> responseWrapper = apiClient.putEntity(Post.class, requestWrapper, "/posts/1");

        softAsserts.assertEquals(responseWrapper.getStatusCode(), 200, "Response status code should be 200");
        softAsserts.assertTrue(JsonUtils.isJsonSchemaValid("postCreateSchema.json", responseWrapper.getResponseRaw().body().asString()));
        softAsserts.assertAll();
    }

    @Test
    public void testPostPatch() {
        Post post = Post.builder().title("title").build();
        RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS).body(post).build();
        ResponseWrapper<Post> responseWrapper = apiClient.patchEntity(Post.class, requestWrapper, "/posts/1");

        softAsserts.assertEquals(responseWrapper.getStatusCode(), 200, "Response status code should be 200");
        softAsserts.assertTrue(JsonUtils.isJsonSchemaValid("postSchema.json", responseWrapper.getResponseRaw().body().asString()));
        softAsserts.assertAll();
    }

    @Test
    public void testPostDeletion() {
        RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS).build();
        ResponseWrapper<Post> responseWrapper = apiClient.deleteEntity(Post.class, requestWrapper, "/posts/1");

        Assert.assertEquals(responseWrapper.getStatusCode(), 200, "Response status code should be 200");
    }
}

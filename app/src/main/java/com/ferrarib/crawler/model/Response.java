package com.ferrarib.crawler.model;

import java.util.List;

/**
 * Created by bruno on 7/11/16.
 */
public class Response {

    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}

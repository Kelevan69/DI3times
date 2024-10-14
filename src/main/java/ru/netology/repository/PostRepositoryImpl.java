package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepositoryImpl implements PostRepository {
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(counter.incrementAndGet());
        }
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public boolean removeById(long id) {
        return posts.remove(id) != null;
    }
}

package com.example.savePost2.Request;

public class allRequest {
    private PostContentRequest pc1;
    private PostRequest pc2;

    public allRequest(PostContentRequest pc1, PostRequest pc2) {
        this.pc1 = pc1;
        this.pc2 = pc2;
    }

    public void setPc1(PostContentRequest pc1) {
        this.pc1 = pc1;
    }

    public void setPc2(PostRequest pc2) {
        this.pc2 = pc2;
    }

    public PostContentRequest getPc1() {
        return pc1;
    }

    public PostRequest getPc2() {
        return pc2;
    }
}

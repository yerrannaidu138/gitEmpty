package com.service;

public class PaginationMetadata {
    private final int currentPage;
    private final int totalPages;
    private String nextLink;
    private String prevLink;

    public PaginationMetadata(int currentPage, int totalPages) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public void setPrevLink(String prevLink) {
        this.prevLink = prevLink;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public String getNextLink() {
        return nextLink;
    }

    public String getPrevLink() {
        return prevLink;
    }
}

package com.test.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * Object used for pagination
 *
 * @author Alejandro Aranda
 *
 */
@JsonInclude(Include.NON_NULL)
public class PageBean<T> {

    private List<T> data;

    private String next;

    /**
     * @return the data
     */
    public List<T> getData() {
        return this.data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * @return the next
     */
    public String getNext() {
        return this.next;
    }

    /**
     * @param next
     *            the next to set
     */
    public void setNext(String next) {
        this.next = next;
    }


}

/**
 * Created by Delgrau
 * 16/11/2023
 *
 * Professor Paulo
 */

package com.example.pdv.dao;

import java.util.ArrayList;

public interface GenericDao<Object> {

    long insert(Object obj);
    long update(Object obj);
    long delete(Object obj);
    ArrayList<Object> getAll();
    Object getById(int id);
}

package com.admin.model;

import java.util.*;

public interface AdmDAO_interface {
	public void insert(AdmVO admVO);
    public void update(AdmVO admVO);
    public void delete(Integer admId);
    public AdmVO findByPrimaryKey(Integer admId);
    public List<AdmVO> getAll();

}

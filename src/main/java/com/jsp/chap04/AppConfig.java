package com.jsp.chap04;

import com.jsp.repository.DancerJdbcRepo;
import com.jsp.repository.DancerMemoryRepo;

// SOLID - OCP 원칙을 위한 클래스
public class AppConfig {

    // 메모리 디비가 필요한 상황
    public DancerMemoryRepo dancerMemoryRepo() {
        return DancerMemoryRepo.getInstance();
    }

    // 실제 디비가 필요한 상황
    public DancerJdbcRepo dancerJdbcRepo() {
        return DancerJdbcRepo.getInstance();
    }

    public AddNewDancerServlet addNewDancerServlet() {
        return new AddNewDancerServlet(dancerMemoryRepo());
    }

    public ShowDancerListServlet showDancerListServlet() {
        return new ShowDancerListServlet(dancerJdbcRepo());
    }

}
